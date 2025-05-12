package com.javaweb.tour_booking.dto;

import com.javaweb.tour_booking.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TouristAttractionDTO {
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long categoryId;

}
