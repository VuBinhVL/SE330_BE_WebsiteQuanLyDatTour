package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByTourRouteId(Long tourRouteId);

}
