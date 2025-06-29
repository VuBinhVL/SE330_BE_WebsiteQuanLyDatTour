package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.FavoriteTour;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteTourRepository extends JpaRepository<FavoriteTour, Long> {
    List<FavoriteTour> findByUserId(Long userId);
    boolean existsByUserIdAndTourRouteId(Long userId, Long tourRouteId);

}
