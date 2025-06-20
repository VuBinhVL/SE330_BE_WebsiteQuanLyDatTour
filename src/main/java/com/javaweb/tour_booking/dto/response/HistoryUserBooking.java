package com.javaweb.tour_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryUserBooking {
    private Long id;
    private int seatsBooked;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long tourId;
    private Long tourRouteId;
    private String tourRouteName;
    private Long userId;
    private Long invoiceId;
    private Boolean paymentStatus;
}
