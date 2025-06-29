package com.javaweb.tour_booking.service;

public interface IEmailService {
    void sendOrderConfirmation(String toEmail, String subject, String contentHtml);
}
