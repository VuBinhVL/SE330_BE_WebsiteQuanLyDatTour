package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.InvoiceDTO;
import com.javaweb.tour_booking.dto.response.InvoiceHistoryResponse;
import com.javaweb.tour_booking.entity.Invoice;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourBooking;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.InvoiceMapper;
import com.javaweb.tour_booking.mapper.TourMapper;
import com.javaweb.tour_booking.repository.InvoiceRepository;
import com.javaweb.tour_booking.repository.TourBookingRepository;
import com.javaweb.tour_booking.service.IInvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public InvoiceDTO GetInvoiceById(Long id) {
        return null;
    }

    @Override
    public InvoiceDTO CreateInvoice(InvoiceDTO newInvoice) {

        Invoice invoice = InvoiceMapper.mapToInvoice(newInvoice,true);
        Invoice saved = invoiceRepository.save(invoice);
        return InvoiceMapper.mapToInvoiceDTO(saved);
    }

    @Override
    public InvoiceDTO UpdateInvoice(long id, InvoiceDTO updatedInvoice) {
        return null;
    }

    @Override
    public void DeleteInvoice(long id) {

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
