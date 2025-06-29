package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.TourRouteAttraction;
import com.javaweb.tour_booking.entity.TouristAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRouteAttractionRepository extends JpaRepository<TourRouteAttraction, Long> {
    @Query("SELECT tra FROM TourRouteAttraction tra " +
            "JOIN FETCH tra.tourRoute " +
            "JOIN FETCH tra.touristAttraction ta " +
            "JOIN FETCH ta.category " +
            "WHERE tra.tourRoute.id = :tourRouteId")

    List<TourRouteAttraction> findByTourRouteId(Long tourRouteId);
    List<TourRouteAttraction> findByTouristAttraction(TouristAttraction touristAttraction);
    List<TourRouteAttraction> findByTourRouteIdOrderByDayAscOrderActionAsc(Long tourRouteId);

}
