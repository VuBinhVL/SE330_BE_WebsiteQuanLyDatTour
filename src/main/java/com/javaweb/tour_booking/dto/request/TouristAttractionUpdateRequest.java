package com.javaweb.tour_booking.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class TouristAttractionUpdateRequest {
    private String name;
    private String location;
    private String description;
    private Long categoryId;
}
