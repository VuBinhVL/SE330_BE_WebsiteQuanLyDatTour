package com.javaweb.tour_booking.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartItemRequest {
    private Long userId;
    private Long tourId;
    private int quantity;
    private LocalDateTime departureDay;
}

