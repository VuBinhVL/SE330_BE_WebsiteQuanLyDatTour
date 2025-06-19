package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
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
    public List<TourDTO> GetAllTours() {
        List<Tour> tour = tourRepository.findAll();
        return tour.stream()
                .map(TourMapper::mapToTourDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TourDTO GetTourById(Long id) {
        Tour tour= tourRepository.getById(id);
        if (tour == null) {
            throw new TouristAttractionNotFound("Không tìm thấy chuyến du lịch");
        }
        return TourMapper.mapToTourDTO(tour);
    }

    @Override
    public TourDTO CreateTour(TourDTO newTour) {
        return null;
    }

    @Override
    public TourDTO UpdateTour(long id, TourDTO updatedTour) {
        return null;
    }

    @Override
    public void DeleteTour(long id) {

    }
}
