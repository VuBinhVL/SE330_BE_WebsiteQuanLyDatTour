package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.dto.TourBookingDetailDTO;

public interface ITourBookingDetailService {
    TourBookingDetailDTO CreateTourBookingDetail(TourBookingDetailDTO tourBookingDetailDTO);
}
