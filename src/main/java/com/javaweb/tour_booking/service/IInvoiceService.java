package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.InvoiceDTO;
import com.javaweb.tour_booking.dto.response.InvoiceHistoryResponse;

import java.util.List;

public interface IInvoiceService {
    List<InvoiceDTO> GetAllInvoices();
    InvoiceDTO GetInvoiceById(Long id);
    InvoiceDTO CreateInvoice(InvoiceDTO newInvoice);
    InvoiceDTO UpdateInvoice(long id,InvoiceDTO updatedInvoice);
    void DeleteInvoice(long id);

    List<InvoiceHistoryResponse> getHistoryInvoiceByUserId(long userId);
}
