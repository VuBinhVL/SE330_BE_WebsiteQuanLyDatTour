package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.TourBooking;
import org.springframework.data.jpa.repository.JpaRepository;


import com.javaweb.tour_booking.dto.response.HistoryUserBooking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TourBookingRepository extends JpaRepository<TourBooking, Long> {

    @Query("SELECT new com.javaweb.tour_booking.dto.response.HistoryUserBooking(" +
            "tb.id, tb.seatsBooked, tb.totalPrice, tb.createdAt, tb.updatedAt, " +
            "t.id, tr.id, tr.routeName, u.id, i.id, i.paymentStatus) " +
            "FROM TourBooking tb " +
            "JOIN tb.user u " +
            "JOIN tb.tour t " +
            "JOIN t.tourRoute tr " +
            "JOIN tb.invoice i " +
            "WHERE u.id = :userId")
    List<HistoryUserBooking> findHistoryByUserId(@Param("userId") Long userId);
    List<TourBooking> findByUserId(Long userId);
}