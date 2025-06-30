package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.ReviewDTO;
import java.util.List;

public interface IReviewService {
    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO updateReview(Long id, ReviewDTO reviewDTO);
    void deleteReview(Long id);
    ReviewDTO getReviewById(Long id);
    List<ReviewDTO> getReviewsByTourRouteId(Long tourRouteId);
    List<ReviewDTO> getReviewsByUserId(Long userId);
}

