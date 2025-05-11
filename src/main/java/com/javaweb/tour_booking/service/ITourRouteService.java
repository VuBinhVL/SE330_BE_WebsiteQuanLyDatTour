package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.TourRouteDTO;
import java.util.List;

public interface ITourRouteService {
    // Lấy hết tất cả các tuyến du lịch
    public List<TourRouteDTO> getAllTourRoutes();

    // Tạo mới 1 tuyến du lịch
    public TourRouteDTO createTourRoute(TourRouteDTO tourRouteDTO);

    // Lấy thông tin 1 tuyến du lịch
    public TourRouteDTO getTourRouteById(Long id);

    // Update thông tin tuyến du lịch
    public TourRouteDTO updateTourRoute(Long id, TourRouteDTO tourRouteDTO);

    // Xóa tuyến du lịch
    public void deleteTourRoute(Long id);
}