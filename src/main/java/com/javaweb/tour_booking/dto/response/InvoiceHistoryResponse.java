package com.javaweb.tour_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class InvoiceHistoryResponse {
    private Long invoiceId;
    private int tourCount;
    private List<Long> tourCodes;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private Boolean paymentStatus;
}
