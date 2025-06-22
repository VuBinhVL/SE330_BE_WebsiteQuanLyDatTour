package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.TouristAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TouristAttractionRepository extends JpaRepository<TouristAttraction, Long> {
    @Query("SELECT ta, SUM(t.bookedSeats) as totalBooked " +
            "FROM TouristAttraction ta " +
            "JOIN TourRouteAttraction tra ON tra.touristAttraction = ta " +
            "JOIN TourRoute tr ON tra.tourRoute = tr " +
            "JOIN Tour t ON t.tourRoute = tr " +
            "GROUP BY ta " +
            "ORDER BY totalBooked DESC")
    List<Object[]> findTop5ByBookedSeats();
}
