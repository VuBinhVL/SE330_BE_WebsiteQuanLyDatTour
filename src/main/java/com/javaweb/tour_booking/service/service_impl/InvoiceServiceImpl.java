package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.InvoiceDTO;
import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.dto.response.InvoiceHistoryResponse;
import com.javaweb.tour_booking.entity.*;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.InvoiceMapper;
import com.javaweb.tour_booking.mapper.TourBookingMapper;
import com.javaweb.tour_booking.mapper.TourMapper;
import com.javaweb.tour_booking.repository.InvoiceRepository;
import com.javaweb.tour_booking.repository.TourBookingRepository;
import com.javaweb.tour_booking.service.IInvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final TourBookingRepository tourBookingRepository;

    @Override
    public List<InvoiceDTO> GetAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(invoice -> {
                    // Truy vấn tất cả TourBooking theo invoiceId
                    List<TourBooking> bookings = tourBookingRepository.findAllByInvoiceId(invoice.getId());

                    // Lấy user từ booking đầu tiên nếu có
                    User user = bookings.isEmpty() ? null : bookings.get(0).getUser();

                    return InvoiceMapper.mapToInvoiceDTO(invoice, user);
                })
                .collect(Collectors.toList());
    }


    @Override
    public InvoiceDTO GetInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));

        // Lấy danh sách TourBooking
        List<TourBooking> bookings = tourBookingRepository.findAllByInvoiceId(id);

        // Lấy User từ booking đầu tiên nếu có
        User user = bookings.isEmpty() ? null : bookings.get(0).getUser();

        // Map sang DTO với user
        InvoiceDTO invoiceDTO = InvoiceMapper.mapToInvoiceDTO(invoice, user);

        // Map danh sách TourBookingDTO
        List<TourBookingDTO> bookingDTOs = bookings.stream()
                .map(TourBookingMapper::mapToTourBookingDTO)
                .collect(Collectors.toList());

        invoiceDTO.setTourBookings(bookingDTOs); // set danh sách booking vào DTO
        return invoiceDTO;
    }

    @Override
    public InvoiceDTO CreateInvoice(InvoiceDTO newInvoice) {

        Invoice invoice = InvoiceMapper.mapToInvoice(newInvoice,true);
        Invoice saved = invoiceRepository.save(invoice);
        return InvoiceMapper.mapToInvoiceDTO(saved);
    }

    @Override
    public InvoiceDTO UpdateInvoice(long id, InvoiceDTO updatedInvoice) {
        Invoice existing = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));

        existing.setPaymentMethod(updatedInvoice.getPaymentMethod());
        existing.setPaymentStatus(updatedInvoice.getPaymentStatus());
        existing.setTotalAmount(updatedInvoice.getTotalAmount());
        existing.setUpdatedAt(LocalDateTime.now());

        Invoice saved = invoiceRepository.save(existing);
        return InvoiceMapper.mapToInvoiceDTO(saved);
    }

    @Override
    public void DeleteInvoice(long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("Invoice not found with id: " + id);
        }
        invoiceRepository.deleteById(id);
    }

    @Override
    public List<InvoiceHistoryResponse> getHistoryInvoiceByUserId(long userId) {
        List<TourBooking> bookings = tourBookingRepository.findByUserId(userId);

        // Gom nhóm theo Invoice
        Map<Long, List<TourBooking>> grouped = bookings.stream()
                .filter(b -> b.getInvoice() != null)
                .collect(Collectors.groupingBy(b -> b.getInvoice().getId()));
        List<InvoiceHistoryResponse> result = new ArrayList<>();
        for (Map.Entry<Long, List<TourBooking>> entry : grouped.entrySet()) {
            var invoice = entry.getValue().get(0).getInvoice();
            var tourCodes = entry.getValue().stream()
                    .map(tb -> tb.getTour().getId())
                    .collect(Collectors.toList());

            int tourCount = entry.getValue().size(); // Đếm số booking thuộc invoice


            result.add(new InvoiceHistoryResponse(
                    invoice.getId(),
                    tourCount,
                    tourCodes,
                    invoice.getTotalAmount(),
                    invoice.getCreatedAt(),
                    invoice.getPaymentStatus()
            ));
        }

        return result;
    }
}
