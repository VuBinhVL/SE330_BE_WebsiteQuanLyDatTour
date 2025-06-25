package com.javaweb.tour_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourRouteSearchResponse {
    private Long id;
    private String name;
    private String code;
    private Integer duration;
    private Integer nights;
    private BigDecimal price;
    private String departure;
    private String destination;
    private List<LocalDate> startDates;
    private String image;
}