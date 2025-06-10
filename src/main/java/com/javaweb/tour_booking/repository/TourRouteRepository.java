package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.dto.FavoriteRouteDTO;
import com.javaweb.tour_booking.entity.TourRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRouteRepository extends JpaRepository<TourRoute, Long> {

    @Query(
            value = """
            SELECT 
                tr.id AS id,
                tr.route_name AS name,
                tr.end_location AS location,
                t_latest.price AS price,
                tr.image AS image
            FROM tour_route tr
            LEFT JOIN (
                SELECT 
                    t.tour_route, 
                    SUM(t.booked_seats) AS total_booked
                FROM tour t
                WHERE t.depature_date >= DATEADD(MONTH, -6, CURRENT_DATE)
                GROUP BY t.tour_route
            ) t_sum ON tr.id = t_sum.tour_route
            LEFT JOIN (
                SELECT t1.tour_route, t1.price
                FROM tour t1
                WHERE t1.depature_date = (
                    SELECT MAX(t2.depature_date)
                    FROM tour t2
                    WHERE t2.tour_route = t1.tour_route
                      AND t2.depature_date >= DATEADD(MONTH, -6, CURRENT_DATE)
                )
            ) t_latest ON tr.id = t_latest.tour_route
            ORDER BY COALESCE(t_sum.total_booked, 0) DESC
            """,
            nativeQuery = true
    )
    List<FavoriteRouteDTO> findFavoriteRoutes();
}