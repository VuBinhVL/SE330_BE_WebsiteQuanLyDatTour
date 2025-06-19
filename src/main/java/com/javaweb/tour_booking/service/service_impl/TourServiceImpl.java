package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.TourRouteMapper;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.repository.TourRouteRepository;
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
    private final TourRouteRepository tourRouteRepository;

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
        if (newTour == null || newTour.getTourRouteId() == null) {
            throw new IllegalArgumentException("TourDTO hoặc tourRouteId không được null");
        }

        // Lấy TourRoute từ tourRouteId
        TourRoute tourRoute = tourRouteRepository.findById(newTour.getTourRouteId())
                .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy tuyến du lịch với ID: " + newTour.getTourRouteId()));

        // Ánh xạ TourDTO sang Tour
        Tour tour = TourMapper.mapToTour(newTour, tourRoute);
        // Lưu vào cơ sở dữ liệu
        Tour saved = tourRepository.save(tour);
        // Ánh xạ ngược lại sang TourDTO
        return TourMapper.mapToTourDTO(saved);
    }

    @Override
    public TourDTO UpdateTour(long id, TourDTO updatedTour) {
        return null;
    }

    @Override
    public void DeleteTour(long id) {

    }
}
