package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.TourRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRouteRepository extends JpaRepository<TourRoute, Long> {
}
