package com.javaweb.tour_booking.service.impl;

import com.javaweb.tour_booking.dto.ReviewDTO;
import com.javaweb.tour_booking.entity.Review;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.repository.ReviewRepository;
import com.javaweb.tour_booking.repository.TourRouteRepository;
import com.javaweb.tour_booking.repository.UserRepository;
import com.javaweb.tour_booking.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TourRouteRepository tourRouteRepository;

    private ReviewDTO toDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getCreatedAt(),
                review.getRating(),
                review.getComment(),
                review.getUser().getId(),
                review.getTourRoute().getId()
        );
    }

    private Review toEntity(ReviewDTO dto) {
        Review review = new Review();
        review.setId(dto.getId());
        review.setCreatedAt(dto.getCreatedAt());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        TourRoute tourRoute = tourRouteRepository.findById(dto.getTourRouteId()).orElseThrow();
        review.setUser(user);
        review.setTourRoute(tourRoute);
        return review;
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = toEntity(reviewDTO);
        review.setCreatedAt(LocalDateTime.now());
        return toDTO(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Optional<Review> optional = reviewRepository.findById(id);
        if (optional.isEmpty()) return null;
        Review review = optional.get();
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        return toDTO(reviewRepository.save(review));
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        return reviewRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<ReviewDTO> getReviewsByTourRouteId(Long tourRouteId) {
        return reviewRepository.findByTourRouteId(tourRouteId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }
}

