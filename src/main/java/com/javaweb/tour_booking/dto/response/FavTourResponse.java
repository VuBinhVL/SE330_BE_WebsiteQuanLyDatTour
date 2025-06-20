package com.javaweb.tour_booking.dto.response;

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
public class FavTourResponse {
    private Long id;
    private LocalDateTime createdAt;
    private Long userID;
    private Long tourRouteId;
    private String tourRouteName;
    private String userFullName;
}