package com.javaweb.tour_booking.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceRequest {
    private Long userId;
    private List<TourOrderDto> tours;
}
