package com.javaweb.tour_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourRouteDetailResponse {
    private Long idTourRoute;
    private String name;
    private String startLocation;
    private String endLocation;
    private boolean isFavorite;
    private List<TourInfo> tours;
    private List<AttractionInfo> itinerary;
}

