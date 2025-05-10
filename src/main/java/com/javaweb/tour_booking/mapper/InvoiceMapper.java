package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.InvoiceDTO;
import com.javaweb.tour_booking.entity.Invoice;

public class InvoiceMapper {
    public static InvoiceDTO mapToInvoiceDTO(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setCreatedAt(invoice.getCreatedAt());
        invoiceDTO.setUpdatedAt(invoice.getUpdatedAt());
        invoiceDTO.setPaymentMethod(invoice.getPaymentMethod());
        invoiceDTO.setPaymentStatus(invoice.getPaymentStatus());
        invoiceDTO.setTotalAmount(invoice.getTotalAmount());
        return invoiceDTO;
    }

    public static Invoice mapToInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDTO.getId());
        invoice.setCreatedAt(invoiceDTO.getCreatedAt());
        invoice.setUpdatedAt(invoiceDTO.getUpdatedAt());
        invoice.setPaymentMethod(invoiceDTO.getPaymentMethod());
        invoice.setPaymentStatus(invoiceDTO.getPaymentStatus());
        invoice.setTotalAmount(invoiceDTO.getTotalAmount());
        return invoice;
    }
}
