package com.javaweb.tour_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeAdminBookingResponse {
    private Long id;
    private String route;
    private String image;
    private int quantity;
    private boolean status;
    private LocalDateTime createdAt;
}