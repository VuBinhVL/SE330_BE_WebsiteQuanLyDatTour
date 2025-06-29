package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Galley;
import com.javaweb.tour_booking.entity.TouristAttraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleyRepository extends JpaRepository<Galley, Long> {
    List<Galley> findByTouristAttraction(TouristAttraction touristAttraction);
    void deleteByTouristAttraction(TouristAttraction touristAttraction);
    List<Galley> findByTouristAttractionIdIn(List<Long> attractionIds);
    List<Galley> findByTouristAttractionId(Long touristAttractionId);


}
