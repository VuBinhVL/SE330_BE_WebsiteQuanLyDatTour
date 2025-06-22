package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.InvoiceDTO;
import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.dto.TourBookingDetailDTO;

public interface IBookingTransactionService {
    void createBookingTransaction(InvoiceDTO invoiceDTO, TourBookingDTO tourBookingDTO, TourBookingDetailDTO tourBookingDetailDTO);
}
