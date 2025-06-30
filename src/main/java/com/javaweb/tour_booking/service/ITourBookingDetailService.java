package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.dto.TourBookingDetailDTO;

import java.util.List;

public interface ITourBookingDetailService {
    TourBookingDetailDTO CreateTourBookingDetail(TourBookingDetailDTO tourBookingDetailDTO);
    List<TourBookingDetailDTO> getAllTourBookingDetails();
    List<TourBookingDetailDTO> getByBookingId(Long bookingId);

}
