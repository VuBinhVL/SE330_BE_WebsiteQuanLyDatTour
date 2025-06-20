package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.dto.response.HistoryUserBooking;
import java.util.List;

public interface ITourBookingService {
    TourBookingDTO createTourBooking(TourBookingDTO tourBookingDTO);
    TourBookingDTO getTourBookingById(Long id);
    TourBookingDTO updateTourBooking(Long id, TourBookingDTO tourBookingDTO);
    void deleteTourBooking(Long id);
    List<TourBookingDTO> getAllTourBookings();
    List<HistoryUserBooking> getHistoryByUserId(Long userId);
}