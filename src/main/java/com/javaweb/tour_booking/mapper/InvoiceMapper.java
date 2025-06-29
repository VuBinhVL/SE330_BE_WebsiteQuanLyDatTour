package com.javaweb.tour_booking.mapper;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.dto.InvoiceDTO;
import com.javaweb.tour_booking.entity.Invoice;

import java.time.LocalDateTime;

public class InvoiceMapper {
    public static InvoiceDTO mapToInvoiceDTO(Invoice invoice) {
        return mapToInvoiceDTO(invoice, null); // fallback
    }

    // OVERLOAD method cho phép truyền user từ booking
    public static InvoiceDTO mapToInvoiceDTO(Invoice invoice, User user) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setCreatedAt(invoice.getCreatedAt());
        invoiceDTO.setUpdatedAt(invoice.getUpdatedAt());
        invoiceDTO.setPaymentMethod(invoice.getPaymentMethod());
        invoiceDTO.setPaymentStatus(invoice.getPaymentStatus());
        invoiceDTO.setTotalAmount(invoice.getTotalAmount());
        invoiceDTO.setIsCanceled(invoice.getIsCanceled());

        if (user != null) {
            invoiceDTO.setUser(UserMapper.mapToUserDTO(user));
            invoiceDTO.setCustomerName(user.getFullname());
        }

        return invoiceDTO;
    }

    public static Invoice mapToInvoice(InvoiceDTO invoiceDTO, boolean isCreate) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDTO.getId());
        invoice.setPaymentMethod(invoiceDTO.getPaymentMethod());
        invoice.setPaymentStatus(invoiceDTO.getPaymentStatus());
        invoice.setTotalAmount(invoiceDTO.getTotalAmount());

        // Logic cho createdAt và updatedAt
        if (isCreate) {
            // Khi tạo mới: createdAt = thời gian hiện tại, updatedAt = null
            invoice.setCreatedAt(LocalDateTime.now());
            invoice.setUpdatedAt(null);
        } else {
            // Khi sửa: giữ nguyên createdAt, cập nhật updatedAt
            invoice.setCreatedAt(invoiceDTO.getCreatedAt());
            invoice.setUpdatedAt(LocalDateTime.now());
        }

        return invoice;
    }
}
