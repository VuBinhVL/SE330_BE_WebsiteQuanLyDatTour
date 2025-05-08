package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.FavoriteTour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteTourRepository extends JpaRepository<FavoriteTour, Long> {
}
