package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.PopularTourRouteDTO;
import com.javaweb.tour_booking.repository.TourRouteRepository;
import com.javaweb.tour_booking.service.IPopularTourRouteHomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PopularTourRouteHomeServiceImpl implements IPopularTourRouteHomeService {

    private final TourRouteRepository tourRouteRepository;

    @Override
    public List<PopularTourRouteDTO> getAllPopularTourRoutes() {
        // Lấy mốc thời gian 6 tháng trước
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);

        // Gọi query đã viết trong repository
        return tourRouteRepository.findTop6PopularTourRoutes(sixMonthsAgo)
                .stream()
                .limit(6)
                .collect(Collectors.toList());

    }
}
