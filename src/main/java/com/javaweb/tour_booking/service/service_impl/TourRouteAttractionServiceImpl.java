package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourRouteAttractionDTO;
import com.javaweb.tour_booking.dto.response.TourRouteAttractionResponse;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.entity.TourRouteAttraction;
import com.javaweb.tour_booking.entity.TouristAttraction;
import com.javaweb.tour_booking.exception.tour.TourCannotBeDeletedException;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.TourRouteAttactionMapper;
import com.javaweb.tour_booking.mapper.TourRouteMapper;
import com.javaweb.tour_booking.repository.TourRouteAttractionRepository;
import com.javaweb.tour_booking.repository.TourRouteRepository;
import com.javaweb.tour_booking.repository.TouristAttractionRepository;
import com.javaweb.tour_booking.service.ITourRouteAttractionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourRouteAttractionServiceImpl implements ITourRouteAttractionService {
    private final TourRouteAttractionRepository  tourRouteAttractionRepository;
    private final TourRouteRepository tourRouteRepository;
    private final TouristAttractionRepository touristAttractionRepository;
    @Override
    @Transactional(readOnly = true)
    public List<TourRouteAttractionResponse> GetAllTourRouteAttractionsByTourRouteId(Long tourRouteId) {
        // Lấy danh sách TourRouteAttraction theo tourRouteId
        List<TourRouteAttraction> tourRouteAttractions = tourRouteAttractionRepository.findByTourRouteId(tourRouteId);

        // Chuyển đổi từ entity sang DTO
        return tourRouteAttractions.stream()
                .map(this::convertToResponse)
                .sorted(Comparator.comparingInt(TourRouteAttractionResponse::getOrderAction))
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
    @Transactional
    public TourRouteAttractionDTO UpdateTourRouteAttractionByTourRouteId(long tourRouteAttractionId, TourRouteAttractionDTO updatedTourRouteAttraction) {
        // Tìm TourRouteAttraction cần cập nhật
        TourRouteAttraction tourRouteAttraction = tourRouteAttractionRepository.findById(tourRouteAttractionId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy lịch trình với ID: " + tourRouteAttractionId));

        // Tìm TourRoute
        TourRoute tourRoute = tourRouteRepository.findById(updatedTourRouteAttraction.getTourRouteId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tuyến du lịch với ID: " + updatedTourRouteAttraction.getTourRouteId()));

        // Tìm TouristAttraction
        TouristAttraction touristAttraction = touristAttractionRepository.findById(updatedTourRouteAttraction.getTouristAttractionId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy điểm tham quan với ID: " + updatedTourRouteAttraction.getTouristAttractionId()));

        // Lấy danh sách tất cả TourRouteAttraction thuộc tourRouteId
        List<TourRouteAttraction> attractions = tourRouteAttractionRepository.findByTourRouteId(updatedTourRouteAttraction.getTourRouteId());

        // Lấy thông tin hiện tại của bản ghi cần cập nhật
        int oldOrderAction = tourRouteAttraction.getOrderAction();
        int oldDay = tourRouteAttraction.getDay();
        int newOrderAction = updatedTourRouteAttraction.getOrderAction();
        int newDay = updatedTourRouteAttraction.getDay();

        // Cập nhật bản ghi hiện tại
        tourRouteAttraction.setOrderAction(newOrderAction);
        tourRouteAttraction.setDay(newDay);
        tourRouteAttraction.setActionDescription(updatedTourRouteAttraction.getActionDescription());
        tourRouteAttraction.setTourRoute(tourRoute);
        tourRouteAttraction.setTouristAttraction(touristAttraction);

        // Điều chỉnh orderAction và day
        if (oldOrderAction != newOrderAction || oldDay != newDay) {
            if (oldDay != newDay) {
                // Cập nhật day cũ
                List<TourRouteAttraction> oldDayAttractions = attractions.stream()
                        .filter(a -> a.getDay() == oldDay && a.getId() != tourRouteAttractionId)
                        .sorted(Comparator.comparingInt(TourRouteAttraction::getOrderAction))
                        .collect(Collectors.toList());
                for (int i = 0; i < oldDayAttractions.size(); i++) {
                    oldDayAttractions.get(i).setOrderAction(i + 1);
                }

                // Cập nhật day mới
                List<TourRouteAttraction> newDayAttractions = attractions.stream()
                        .filter(a -> a.getDay() == newDay && a.getId() != tourRouteAttractionId)
                        .sorted(Comparator.comparingInt(TourRouteAttraction::getOrderAction))
                        .collect(Collectors.toList());
                for (int i = 0; i < newDayAttractions.size(); i++) {
                    TourRouteAttraction attr = newDayAttractions.get(i);
                    if (attr.getOrderAction() >= newOrderAction) {
                        attr.setOrderAction(i + 2); // +2 để nhường chỗ cho bản ghi mới
                    } else {
                        attr.setOrderAction(i + 1);
                    }
                }
            } else {
                // Cập nhật orderAction trong cùng day
                List<TourRouteAttraction> sameDayAttractions = attractions.stream()
                        .filter(a -> a.getDay() == newDay && a.getId() != tourRouteAttractionId)
                        .sorted(Comparator.comparingInt(TourRouteAttraction::getOrderAction))
                        .collect(Collectors.toList());

                if (newOrderAction < oldOrderAction) {
                    // Ví dụ: 5 → 3, tăng các orderAction từ 3 đến 4 lên 1
                    for (TourRouteAttraction attr : sameDayAttractions) {
                        if (attr.getOrderAction() >= newOrderAction && attr.getOrderAction() < oldOrderAction) {
                            attr.setOrderAction(attr.getOrderAction() + 1);
                        }
                    }
                } else {
                    // Ví dụ: 3 → 5, giảm các orderAction từ 4 đến 5 đi 1
                    for (TourRouteAttraction attr : sameDayAttractions) {
                        if (attr.getOrderAction() > oldOrderAction && attr.getOrderAction() <= newOrderAction) {
                            attr.setOrderAction(attr.getOrderAction() - 1);
                        }
                    }
                }
            }

            // Lưu tất cả các bản ghi đã thay đổi
            tourRouteAttractionRepository.saveAll(attractions);
        }

        // Lưu bản ghi chính đã cập nhật
        TourRouteAttraction updatedEntity = tourRouteAttractionRepository.save(tourRouteAttraction);

        // Chuyển đổi entity đã cập nhật thành DTO để trả về
        return TourRouteAttactionMapper.mapToTourRouteAttractionDTO(updatedEntity);
    }
    @Override
    @Transactional
    public void DeleteTourRouteAttraction(long id) {
        // Tìm TourRouteAttraction cần xóa
        TourRouteAttraction tourRouteAttraction = tourRouteAttractionRepository.findById(id)
                .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy lịch trình với ID: " + id));

        // Lấy tourRouteId và day của bản ghi cần xóa
        Long tourRouteId = tourRouteAttraction.getTourRoute().getId();
        int day = tourRouteAttraction.getDay();
        int deletedOrderAction = tourRouteAttraction.getOrderAction();

        // Xóa bản ghi
        tourRouteAttractionRepository.deleteById(id);

        // Lấy danh sách tất cả TourRouteAttraction còn lại trong cùng tourRouteId và day
        List<TourRouteAttraction> remainingAttractions = tourRouteAttractionRepository.findByTourRouteId(tourRouteId)
                .stream()
                .filter(a -> a.getDay() == day)
                .sorted(Comparator.comparingInt(TourRouteAttraction::getOrderAction))
                .collect(Collectors.toList());

        // Cập nhật lại orderAction cho các bản ghi còn lại
        for (int i = 0; i < remainingAttractions.size(); i++) {
            TourRouteAttraction attr = remainingAttractions.get(i);
            if (attr.getOrderAction() > deletedOrderAction) {
                attr.setOrderAction(attr.getOrderAction() - 1);
            }
        }

        // Lưu các bản ghi đã cập nhật
        if (!remainingAttractions.isEmpty()) {
            tourRouteAttractionRepository.saveAll(remainingAttractions);
        }
    }
}
