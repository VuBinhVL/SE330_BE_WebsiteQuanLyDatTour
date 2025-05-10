package com.javaweb.tour_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourRouteAttractionDTO {
    private Long id;
    private int order;
    private String actionDescription;
    private Long tourRouteId;
    private Long touristAttractionId;
}
