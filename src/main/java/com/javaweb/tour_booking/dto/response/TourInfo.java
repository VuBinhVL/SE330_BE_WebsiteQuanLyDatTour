package com.javaweb.tour_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourInfo {
    private Long idTour;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private BigDecimal price;
    private int totalSeats;
    private int bookedSeats;
    private String pickUpLocation;
    private String pickUpTime;
    private long duration;
    private List<String> galleries; // URLs hoặc paths
}