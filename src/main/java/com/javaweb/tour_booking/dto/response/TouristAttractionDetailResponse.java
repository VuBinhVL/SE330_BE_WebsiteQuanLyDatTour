package com.javaweb.tour_booking.dto.response;

import com.javaweb.tour_booking.entity.Galley;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TouristAttractionDetailResponse {
    private Long id;
    private String name;
    private String location;
    private String categoryName;
    private Long categoryId;
    private String description;
    private List<String> galleries;
}
