package com.javaweb.tour_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRouteDTO {
    private Long id;
    private String name;
    private String location;
    private BigDecimal price;
    private String image;
}