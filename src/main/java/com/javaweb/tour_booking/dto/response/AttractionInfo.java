package com.javaweb.tour_booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionInfo {
    private int day;
    private int order;
    private String attractionName;
    private List<String> galleries; // URLs hoặc paths
}