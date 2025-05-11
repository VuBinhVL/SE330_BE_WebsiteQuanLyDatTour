
package com.javaweb.tour_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularTourRouteDTO {
    private Long id;
    private String routeName;
    private String startLocation;
    private String endLocation;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long totalBookings;
    private BigDecimal latestTourPrice;
}