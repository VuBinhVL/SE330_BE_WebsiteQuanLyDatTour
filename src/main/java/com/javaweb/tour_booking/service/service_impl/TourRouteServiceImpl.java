package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.exception.tour_route.TourRouteNotFound;
import com.javaweb.tour_booking.mapper.TourRouteMapper;
import com.javaweb.tour_booking.repository.TourRouteRepository;
import com.javaweb.tour_booking.service.ITourRouteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourRouteServiceImpl implements ITourRouteService {
    private final TourRouteRepository tourRouteRepository;
    
    @Override
    public List<TourRouteDTO> getAllTourRoutes() {
        List<TourRoute> tourRouteList = tourRouteRepository.findAll();
        return tourRouteList.stream()
                .map(TourRouteMapper::mapToTourRouteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TourRouteDTO createTourRoute(TourRouteDTO tourRouteDTO) {
        TourRoute tourRoute = TourRouteMapper.mapToTourRoute(tourRouteDTO);
        TourRoute savedTourRoute = tourRouteRepository.save(tourRoute);
        return TourRouteMapper.mapToTourRouteDTO(savedTourRoute);
    }

    @Override
    public TourRouteDTO getTourRouteById(Long id) {
        TourRoute tourRoute = tourRouteRepository.findById(id).orElse(null);
        if (tourRoute == null) {
            throw new TourRouteNotFound("Không tìm thấy tuyến du lịch");
        }
        return TourRouteMapper.mapToTourRouteDTO(tourRoute);
    }

    @Override
    public TourRouteDTO updateTourRoute(Long id, TourRouteDTO tourRouteDTO) {
        TourRoute tourRoute = tourRouteRepository.findById(id).orElse(null);
        if (tourRoute == null) {
            throw new TourRouteNotFound("Không tìm thấy tuyến du lịch");
        }
        tourRoute.setRouteName(tourRouteDTO.getRouteName());
        tourRoute.setStartLocation(tourRouteDTO.getStartLocation());
        tourRoute.setEndLocation(tourRouteDTO.getEndLocation());
        tourRoute.setStartDate(tourRouteDTO.getStartDate());
        tourRoute.setEndDate(tourRouteDTO.getEndDate());
        tourRoute.setCreatedAt(tourRouteDTO.getCreatedAt());
        tourRoute.setUpdatedAt(tourRouteDTO.getUpdatedAt());
        TourRoute savedTourRoute = tourRouteRepository.save(tourRoute);
        return TourRouteMapper.mapToTourRouteDTO(savedTourRoute);
    }

    @Override
    public void deleteTourRoute(Long id) {
        TourRoute tourRoute = tourRouteRepository.findById(id).orElse(null);
        if (tourRoute == null) {
            throw new TourRouteNotFound("Không tìm thấy tuyến du lịch");
        }
        tourRouteRepository.deleteById(id);
    }
}