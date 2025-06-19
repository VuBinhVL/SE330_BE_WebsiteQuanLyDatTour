package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.response.TouristAttractionDetailResponse;
import com.javaweb.tour_booking.dto.response.TouristAttractionResponse;
import com.javaweb.tour_booking.entity.TouristAttraction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITouristAttractionService {
    public List<TouristAttractionResponse> getAllTouristAttractions();

    public TouristAttractionDetailResponse getTouristAttractionById(Long id);

    public void deleteTouristAttractionById(Long id);

    public void createTouristAttraction(String name, String location, String description, Long categoryId, List<MultipartFile> images);
}
