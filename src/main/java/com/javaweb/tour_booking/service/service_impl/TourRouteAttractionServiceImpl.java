package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourRouteAttractionDTO;
import com.javaweb.tour_booking.dto.response.TourRouteAttractionResponse;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.entity.TourRouteAttraction;
import com.javaweb.tour_booking.mapper.TourRouteAttactionMapper;
import com.javaweb.tour_booking.mapper.TourRouteMapper;
import com.javaweb.tour_booking.repository.TourRouteAttractionRepository;
import com.javaweb.tour_booking.service.ITourRouteAttractionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourRouteAttractionServiceImpl implements ITourRouteAttractionService {
    private final TourRouteAttractionRepository  tourRouteAttractionRepository;
    @Override
    public List<TourRouteAttractionResponse> GetAllTourRouteAttractionsByTourRouteId(Long tourRouteId) {
        // Lấy danh sách TourRouteAttraction theo tourRouteId
        List<TourRouteAttraction> tourRouteAttractions = tourRouteAttractionRepository.findByTourRouteId(tourRouteId);

        // Chuyển đổi từ entity sang DTO
        return tourRouteAttractions.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Hàm chuyển đổi từ entity sang DTO
    private TourRouteAttractionResponse convertToResponse(TourRouteAttraction tourRouteAttraction) {
        TourRouteAttractionResponse response = new TourRouteAttractionResponse();
        response.setId(tourRouteAttraction.getId());
        response.setTourRoute(tourRouteAttraction.getTourRoute());
        response.setTouristAttraction(tourRouteAttraction.getTouristAttraction());
        response.setCategory(tourRouteAttraction.getTouristAttraction().getCategory());
        response.setOrderAction(tourRouteAttraction.getOrderAction());
        response.setActionDescription(tourRouteAttraction.getActionDescription());
        response.setDay(tourRouteAttraction.getDay());
        return response;
    }

    @Override
    public TourRouteAttractionDTO GetTourRouteAttractionById(Long id) {
        return null;
    }

    @Override
    public TourRouteAttractionDTO CreateTourRouteAttraction(TourRouteAttractionDTO newTourRouteAttraction) {
        return null;
    }

    @Override
    public TourRouteAttractionDTO UpdateTourRouteAttraction(long id, TourRouteAttractionDTO updatedTourRouteAttraction) {
        return null;
    }

    @Override
    public void DeleteTourRouteAttraction(long id) {

    }
}
