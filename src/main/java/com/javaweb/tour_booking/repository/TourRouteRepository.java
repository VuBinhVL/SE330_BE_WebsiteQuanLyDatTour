package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.TourRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.javaweb.tour_booking.dto.PopularTourRouteDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface TourRouteRepository extends JpaRepository<TourRoute, Long> {
    @Query("""
    SELECT new com.javaweb.tour_booking.dto.PopularTourRouteDTO(
        tr.id, tr.routeName, tr.startLocation, tr.endLocation, 
        tr.startDate, tr.endDate,
        COUNT(tbd.id),
        MAX(t.price))
    FROM TourRoute tr
    LEFT JOIN Tour t ON t.tourRoute.id = tr.id
    LEFT JOIN TourBooking tb ON tb.tour.id = t.id
    LEFT JOIN TourBookingDetail tbd ON tbd.tourBooking.id = tb.id
    WHERE t.depatureDate >= :sixMonthsAgo OR t.depatureDate IS NULL
    GROUP BY tr.id, tr.routeName, tr.startLocation, tr.endLocation, tr.startDate, tr.endDate
    ORDER BY COUNT(tbd.id) DESC
    """)
    List<PopularTourRouteDTO> findTop6PopularTourRoutes(LocalDateTime sixMonthsAgo);
}
