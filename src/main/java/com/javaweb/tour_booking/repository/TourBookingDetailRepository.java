package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.TourBookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourBookingDetailRepository extends JpaRepository<TourBookingDetail, Long> {
}
