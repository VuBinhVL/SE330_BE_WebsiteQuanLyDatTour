package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.TourBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourBookingRepository extends JpaRepository<TourBooking, Long> {
}
