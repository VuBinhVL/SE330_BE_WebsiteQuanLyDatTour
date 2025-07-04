package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.FavoriteRouteDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.dto.response.TourRouteDetailResponse;
import com.javaweb.tour_booking.dto.response.TourRouteSearchResponse;


import java.util.List;

public interface ITourRouteService {
    // Get all tour routes
    List<TourRouteDTO> getAllTourRoutes();

    // Create a new tour route
    TourRouteDTO createTourRoute(TourRouteDTO tourRouteDTO);

    // Get a tour route by ID
    TourRouteDTO getTourRouteById(Long id);

    // Update a tour route
    TourRouteDTO updateTourRoute(Long id, TourRouteDTO tourRouteDTO);

    // Delete a tour route
    void deleteTourRoute(Long id);

    // Get top 4 favorite routes
    List<FavoriteRouteDTO> getTop4FavoriteRoutes();

    // Search tour routes with custom response
    List<TourRouteSearchResponse> searchTourRoutes();

    //Hiển thị thông tin chi tiết của 1 tourRoute
    public TourRouteDetailResponse getDetail(Long routeId, Long userId);
}