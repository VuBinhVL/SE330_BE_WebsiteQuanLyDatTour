package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.TourRouteAttractionDTO;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.entity.TourRouteAttraction;
import com.javaweb.tour_booking.entity.TouristAttraction;

public class TourRouteAttactionMapper {
    public static TourRouteAttractionDTO mapToTourRouteAttractionDTO(TourRouteAttraction entity) {
        TourRouteAttractionDTO dto = new TourRouteAttractionDTO();
        dto.setId(entity.getId());
        dto.setOrder(entity.getOrder());
        dto.setActionDescription(entity.getActionDescription());
        dto.setTourRouteId(entity.getTourRoute().getId());
        dto.setTouristAttractionId(entity.getTouristAttraction().getId());
        return dto;
    }

    public static TourRouteAttraction mapToTourRouteAttraction(
            TourRouteAttractionDTO dto,
            TourRoute tourRoute,
            TouristAttraction touristAttraction) {
        TourRouteAttraction entity = new TourRouteAttraction();
        entity.setId(dto.getId());
        entity.setOrder(dto.getOrder());
        entity.setActionDescription(dto.getActionDescription());
        entity.setTourRoute(tourRoute);
        entity.setTouristAttraction(touristAttraction);
        return entity;
    }
}
