package com.javaweb.tour_booking.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class TourOrderDto {
    private Long tourId;
    private String departureDate;
    private int quantity;
    private int contactIndex;
    private List<PassengerDto> passengers;
}