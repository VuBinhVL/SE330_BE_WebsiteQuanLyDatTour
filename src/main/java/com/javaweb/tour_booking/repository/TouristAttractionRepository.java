package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.TouristAttraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristAttractionRepository extends JpaRepository<TouristAttraction, Long> {
}
