package com.javaweb.tour_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourBookingDTO {
    private Long id;
    private int seatsBooked;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long tourId;
    private Long userId;
    private Long invoiceId;
    private UserDTO user;
    private TourDTO tour;
    private TourRouteDTO tourRoute;
    private int tourStatus;

}
