package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.TourBookingDTO;

import java.util.List;

public interface ITourBookingService {

    // Lấy tất cả đơn đặt tour
    List<TourBookingDTO> getAllBookings();

    // Lấy đơn đặt tour theo ID
    TourBookingDTO getBookingById(Long id);

    // Xoá đơn đặt tour theo ID
    void deleteBookingById(Long id);
}
