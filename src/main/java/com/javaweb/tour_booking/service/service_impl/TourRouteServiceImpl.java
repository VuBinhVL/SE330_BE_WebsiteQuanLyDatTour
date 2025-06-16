package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.FavoriteRouteDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
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
        List<TourRoute> tourRoutes = tourRouteRepository.findAll();
        return tourRoutes.stream()
                .map(TourRouteMapper::mapToTourRouteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TourRouteDTO createTourRoute(TourRouteDTO tourRouteDTO) {
        TourRoute tourRoute = TourRouteMapper.mapToTourRoute(tourRouteDTO);
        TourRoute saved = tourRouteRepository.save(tourRoute);
        return TourRouteMapper.mapToTourRouteDTO(saved);
    }

    @Override
    public TourRouteDTO getTourRouteById(Long id) {
        TourRoute tourRoute = tourRouteRepository.findById(id).orElse(null);
        if (tourRoute == null) {
            throw new TouristAttractionNotFound("Không tìm thấy tuyến du lịch");
        }
        return TourRouteMapper.mapToTourRouteDTO(tourRoute);
    }

    @Override
    public TourRouteDTO updateTourRoute(Long id, TourRouteDTO tourRouteDTO) {
        TourRoute tourRoute = tourRouteRepository.findById(id).orElse(null);
        if (tourRoute == null) {
            throw new TouristAttractionNotFound("Không tìm thấy tuyến du lịch");
        }
        tourRoute.setRouteName(tourRouteDTO.getRouteName());
        tourRoute.setStartLocation(tourRouteDTO.getStartLocation());
        tourRoute.setEndLocation(tourRouteDTO.getEndLocation());
        tourRoute.setImage(tourRouteDTO.getImage());
        tourRoute.setStartDate(tourRouteDTO.getStartDate());
        tourRoute.setEndDate(tourRouteDTO.getEndDate());
        tourRoute.setCreatedAt(tourRouteDTO.getCreatedAt());
        tourRoute.setUpdatedAt(tourRouteDTO.getUpdatedAt());
        TourRoute saved = tourRouteRepository.save(tourRoute);
        return TourRouteMapper.mapToTourRouteDTO(saved);
    }

    @Override
    public void deleteTourRoute(Long id) {
        TourRoute tourRoute = tourRouteRepository.findById(id).orElse(null);
        if (tourRoute == null) {
            throw new TouristAttractionNotFound("Không tìm thấy tuyến du lịch");
        }
        tourRouteRepository.deleteById(id);
    }

    @Override
    public List<FavoriteRouteDTO> getTop4FavoriteRoutes() {
        List<FavoriteRouteDTO> favoriteRoutes = tourRouteRepository.findFavoriteRoutes();
        return favoriteRoutes.stream().limit(4).collect(Collectors.toList());
    }
}