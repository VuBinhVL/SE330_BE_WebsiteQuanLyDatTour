package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.PopularTourRouteDTO ;
import java.util.List;

public interface IPopularTourRouteHomeService {
    public List<PopularTourRouteDTO> getAllPopularTourRoutes();
}