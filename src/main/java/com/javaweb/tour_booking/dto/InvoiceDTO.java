package com.javaweb.tour_booking.dto;

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
public class InvoiceDTO {
    private Long id;
    private BigDecimal totalAmount;
    private Boolean paymentStatus;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isCanceled;

    private UserDTO user;
    private String customerName;
    private List<TourBookingDTO> tourBookings;
}
