package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.TourRouteAttraction;
import com.javaweb.tour_booking.entity.TouristAttraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRouteAttractionRepository extends JpaRepository<TourRouteAttraction, Long> {
    List<TourRouteAttraction> findByTouristAttraction(TouristAttraction touristAttraction);
}
