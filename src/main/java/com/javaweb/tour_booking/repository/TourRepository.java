package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
}
