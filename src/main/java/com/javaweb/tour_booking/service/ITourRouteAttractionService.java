package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.dto.TourRouteAttractionDTO;
import com.javaweb.tour_booking.dto.response.TourRouteAttractionResponse;

import java.util.List;

public interface ITourRouteAttractionService {
    List<TourRouteAttractionResponse> GetAllTourRouteAttractionsByTourRouteId(Long tourRouteId);
    TourRouteAttractionDTO GetTourRouteAttractionById(Long id);
    TourRouteAttractionDTO CreateTourRouteAttractionByTourRouteId(TourRouteAttractionDTO newTourRouteAttraction);
    TourRouteAttractionDTO UpdateTourRouteAttraction(long id,TourRouteAttractionDTO updatedTourRouteAttraction);
    void DeleteTourRouteAttraction(long id);
}
