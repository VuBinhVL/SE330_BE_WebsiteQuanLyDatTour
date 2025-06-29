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
public class CartItemResponse {
    private Long id;
    private Long tourRouteId;
    private String routeName;
    private String routeImage;
    private String startLocation;
    private String endLocation;
    private Long tourId;
    private String duration; // vd: "5 ngày"
    private LocalDate departureDates;
    private BigDecimal price;
    private int quantity;
    private int availableSeats;
}
