package com.javaweb.tour_booking.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PopularTourRouteResponse {
    private Long id;
    private String routeName;
    private String startLocation;
    private String endLocation;
    private Long totalBookedSeats;
    private BigDecimal latestPrice;
    private String image;
    private Long durationDays;
    private List<LocalDateTime> recentStartDates;
}
