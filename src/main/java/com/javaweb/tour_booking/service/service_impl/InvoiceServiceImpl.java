package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.InvoiceDTO;
import com.javaweb.tour_booking.entity.Invoice;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.InvoiceMapper;
import com.javaweb.tour_booking.mapper.TourMapper;
import com.javaweb.tour_booking.repository.InvoiceRepository;
import com.javaweb.tour_booking.service.IInvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {
    private final InvoiceRepository invoiceRepository;
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
}
