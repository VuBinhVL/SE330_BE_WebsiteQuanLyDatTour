package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.mapper.TourRouteMapper;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.service.ITourService;
import com.javaweb.tour_booking.mapper.TourMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourServiceImpl implements ITourService {
    private final TourRepository tourRepository;

    @Override
    public List<TourDTO> getAllTours() {
        List<Tour> tour = tourRepository.findAll();
        return tour.stream()
                .map(TourMapper::mapToTourDTO)
                .collect(Collectors.toList());
    }
}
