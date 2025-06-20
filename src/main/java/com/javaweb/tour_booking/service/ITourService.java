package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;

import java.util.List;

public interface ITourService {
    List<TourDTO> GetAllTours();
    TourDTO GetTourById(Long id);
    TourDTO CreateTour(TourDTO newTour);
    TourDTO UpdateTour(long id,TourDTO updatedTour);
    void DeleteTour(long id);

}
