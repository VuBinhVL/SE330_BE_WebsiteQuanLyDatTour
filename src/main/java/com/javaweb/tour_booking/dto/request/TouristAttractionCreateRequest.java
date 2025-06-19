package com.javaweb.tour_booking.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class TouristAttractionCreateRequest {
    private String name;
    private String location;
    private String description;
    private Long categoryId;
    private List<MultipartFile> images;
}
