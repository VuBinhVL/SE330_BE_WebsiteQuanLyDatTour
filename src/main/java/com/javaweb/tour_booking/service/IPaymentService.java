package com.javaweb.tour_booking.service;


import com.javaweb.tour_booking.dto.request.InvoiceRequest;

public interface IPaymentService {
    void createInvoiceWithBookings(InvoiceRequest request);
}
