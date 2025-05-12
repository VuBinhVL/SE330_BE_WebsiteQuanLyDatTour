package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.entity.TourRoute;

public class TourRouteMapper {
    public static TourRouteDTO mapToTourRouteDTO(TourRoute  route) {
        TourRouteDTO dto = new TourRouteDTO();
        dto.setId(route.getId());
        dto.setRouteName(route.getRouteName());
        dto.setStartLocation(route.getStartLocation());
        dto.setEndLocation(route.getEndLocation());
        dto.setStartDate(route.getStartDate());
        dto.setEndDate(route.getEndDate());
        dto.setImage(route.getImage());
        dto.setCreatedAt(route.getCreatedAt());
        dto.setUpdatedAt(route.getUpdatedAt());
        return dto;
    }

    public static TourRoute mapToTourRoute(TourRouteDTO dto) {
        TourRoute route = new TourRoute();
        route.setId(dto.getId());
        route.setRouteName(dto.getRouteName());
        route.setStartLocation(dto.getStartLocation());
        route.setEndLocation(dto.getEndLocation());
        route.setStartDate(dto.getStartDate());
        route.setEndDate(dto.getEndDate());
        route.setImage(dto.getImage());
        route.setCreatedAt(dto.getCreatedAt());
        route.setUpdatedAt(dto.getUpdatedAt());
        return route;
    }
}
