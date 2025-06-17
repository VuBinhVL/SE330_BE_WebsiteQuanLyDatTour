package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TouristAttractionDTO;
import com.javaweb.tour_booking.dto.response.TouristAttractionResponse;
import com.javaweb.tour_booking.entity.TouristAttraction;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.TouristAttractionMapper;
import com.javaweb.tour_booking.repository.TouristAttractionRepository;
import com.javaweb.tour_booking.service.ITouristAttractionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TouristAttractionServiceImpl implements ITouristAttractionService {
    private TouristAttractionRepository touristAttractionRepository;

    @Override
    public List<TouristAttractionResponse> getAllTouristAttractions() {
        return touristAttractionRepository.findAll().stream()
                .map(attraction -> new TouristAttractionResponse(
                        attraction.getId(),
                        attraction.getName(),
                        attraction.getLocation(),
                        attraction.getCategory().getName()
                )).collect(Collectors.toList());
    }
}
