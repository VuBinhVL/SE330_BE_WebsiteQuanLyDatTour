package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.response.TouristAttractionDetailResponse;
import com.javaweb.tour_booking.dto.response.TouristAttractionResponse;

import java.util.List;

public interface ITouristAttractionService {
    public List<TouristAttractionResponse> getAllTouristAttractions();

    public TouristAttractionDetailResponse getTouristAttractionById(Long id);
}
