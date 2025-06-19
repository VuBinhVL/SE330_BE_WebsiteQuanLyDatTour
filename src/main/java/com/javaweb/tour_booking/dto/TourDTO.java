package com.javaweb.tour_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourDTO {
    private Long id;
    private LocalDateTime depatureDate;
    private LocalDateTime returnDate;
    private int status;
    private BigDecimal price;
    private int totalSeats;
    private int bookedSeats;
    private LocalDateTime pickUpTime;
    private String pickUpLocation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long tourRouteId;
}
