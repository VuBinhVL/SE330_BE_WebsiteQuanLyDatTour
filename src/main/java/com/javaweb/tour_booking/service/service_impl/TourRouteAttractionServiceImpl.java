package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourRouteAttractionDTO;
import com.javaweb.tour_booking.dto.response.TourRouteAttractionResponse;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.entity.TourRouteAttraction;
import com.javaweb.tour_booking.entity.TouristAttraction;
import com.javaweb.tour_booking.mapper.TourRouteAttactionMapper;
import com.javaweb.tour_booking.mapper.TourRouteMapper;
import com.javaweb.tour_booking.repository.TourRouteAttractionRepository;
import com.javaweb.tour_booking.repository.TourRouteRepository;
import com.javaweb.tour_booking.repository.TouristAttractionRepository;
import com.javaweb.tour_booking.service.ITourRouteAttractionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourRouteAttractionServiceImpl implements ITourRouteAttractionService {
    private final TourRouteAttractionRepository  tourRouteAttractionRepository;
    private final TourRouteRepository tourRouteRepository;
    private final TouristAttractionRepository touristAttractionRepository;
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
    public TourRouteAttractionDTO CreateTourRouteAttractionByTourRouteId(TourRouteAttractionDTO newTourRouteAttraction) {
        // Tìm TourRoute theo tourRouteId
        TourRoute tourRoute = tourRouteRepository.findById(newTourRouteAttraction.getTourRouteId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tuyến du lịch với ID: " + newTourRouteAttraction.getTourRouteId()));

        // Tìm TouristAttraction theo touristAttractionId
        TouristAttraction touristAttraction = touristAttractionRepository.findById(newTourRouteAttraction.getTouristAttractionId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy điểm tham quan với ID: " + newTourRouteAttraction.getTouristAttractionId()));

        // Chuyển đổi DTO sang entity
        TourRouteAttraction tourRouteAttraction = TourRouteAttactionMapper.mapToTourRouteAttraction(newTourRouteAttraction, tourRoute, touristAttraction);

        // Lưu entity vào database
        TourRouteAttraction savedTourRouteAttraction = tourRouteAttractionRepository.save(tourRouteAttraction);

        // Chuyển đổi entity đã lưu thành DTO để trả về
        return TourRouteAttactionMapper.mapToTourRouteAttractionDTO(savedTourRouteAttraction);
    }

    @Override
    public TourRouteAttractionDTO UpdateTourRouteAttractionByTourRouteId(long tourRouteAttractionId, TourRouteAttractionDTO updatedTourRouteAttraction) {
        // Tìm TourRouteAttraction theo ID
        TourRouteAttraction tourRouteAttraction = tourRouteAttractionRepository.findById(tourRouteAttractionId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy lịch trình với ID: " + tourRouteAttractionId));

        // Tìm TourRoute theo tourRouteId
        TourRoute tourRoute = tourRouteRepository.findById(updatedTourRouteAttraction.getTourRouteId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tuyến du lịch với ID: " + updatedTourRouteAttraction.getTourRouteId()));

        // Tìm TouristAttraction theo touristAttractionId
        TouristAttraction touristAttraction = touristAttractionRepository.findById(updatedTourRouteAttraction.getTouristAttractionId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy điểm tham quan với ID: " + updatedTourRouteAttraction.getTouristAttractionId()));

        // Cập nhật các trường của TourRouteAttraction
        tourRouteAttraction.setOrderAction(updatedTourRouteAttraction.getOrderAction());
        tourRouteAttraction.setDay(updatedTourRouteAttraction.getDay());
        tourRouteAttraction.setActionDescription(updatedTourRouteAttraction.getActionDescription());
        tourRouteAttraction.setTourRoute(tourRoute);
        tourRouteAttraction.setTouristAttraction(touristAttraction);

        // Lưu entity đã cập nhật vào database
        TourRouteAttraction updatedEntity = tourRouteAttractionRepository.save(tourRouteAttraction);

        // Chuyển đổi entity đã cập nhật thành DTO để trả về
        return TourRouteAttactionMapper.mapToTourRouteAttractionDTO(updatedEntity);
    }


    @Override
    public void DeleteTourRouteAttraction(long id) {

    }
}
