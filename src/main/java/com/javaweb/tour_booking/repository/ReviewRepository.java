package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTourRouteId(Long tourRouteId);
    List<Review> findByUserId(Long userId);
}

