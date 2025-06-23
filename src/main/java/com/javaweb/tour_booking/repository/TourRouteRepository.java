package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.dto.FavoriteRouteDTO;
import com.javaweb.tour_booking.entity.TourRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
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

    @Query(
            value = """
                        SELECT 
                            tr.id,
                            tr.route_name,
                            tr.start_location, 
                            tr.end_location,
                            SUM(t.booked_seats) as total_booked,
                            (SELECT t2.price FROM tour t2 WHERE t2.tour_route_id = tr.id AND t2.depature_date >= :sixMonthsAgo AND t2.depature_date >= CURRENT_DATE ORDER BY t2.depature_date DESC LIMIT 1) as latest_price,
                            tr.image,
                            (SELECT DATEDIFF(t2.return_date, t2.depature_date) + 1 FROM tour t2 WHERE t2.tour_route_id = tr.id AND t2.depature_date >= :sixMonthsAgo AND t2.depature_date >= CURRENT_DATE ORDER BY t2.depature_date DESC LIMIT 1) as duration_days,
                            (SELECT GROUP_CONCAT(t3.depature_date ORDER BY t3.depature_date DESC SEPARATOR ',') FROM tour t3 WHERE t3.tour_route_id = tr.id AND t3.depature_date >= :sixMonthsAgo AND t3.depature_date >= CURRENT_DATE GROUP BY t3.tour_route_id LIMIT 1) as recent_start_dates
                        FROM tour_route tr
                        JOIN tour t ON t.tour_route_id = tr.id
                        WHERE t.depature_date >= :sixMonthsAgo
                        AND EXISTS (
                            SELECT 1 FROM tour t4 
                            WHERE t4.tour_route_id = tr.id 
                            AND t4.depature_date >= CURRENT_DATE
                        )
                        GROUP BY tr.id
                        ORDER BY total_booked DESC
                        LIMIT 5
                    """,
            nativeQuery = true
    )
    List<Object[]> findTop5PopularTourRoutes(@Param("sixMonthsAgo") LocalDateTime sixMonthsAgo);
}