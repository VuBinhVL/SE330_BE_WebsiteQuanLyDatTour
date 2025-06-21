package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.TourBookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourBookingDetailRepository extends JpaRepository<TourBookingDetail, Long> {
    @Query("SELECT tbd FROM TourBookingDetail tbd " +
            "JOIN FETCH tbd.tourBooking tb " +
            "JOIN FETCH tb.tour " +
            "JOIN FETCH tb.user " +
            "JOIN FETCH tbd.userMember " +
            "WHERE tb.tour.id = :tourId")
    List<TourBookingDetail> findByTourBookingTourId(Long tourId);
}