package com.javaweb.tour_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteTourDTO {
    private Long id;
    private LocalDateTime createdAt;
    private Long userID;
    private Long tourRouteId;
}
