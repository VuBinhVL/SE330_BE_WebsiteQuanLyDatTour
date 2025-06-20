package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.FavoriteTourDTO;
import com.javaweb.tour_booking.dto.response.FavTourResponse;

import java.util.List;

public interface IFavoriteTourService {
    List<FavoriteTourDTO> getAllFavoriteTours();
    FavoriteTourDTO getFavoriteTourById(Long id);
    FavoriteTourDTO addFavoriteTour(FavoriteTourDTO favoriteTourDTO);
    void removeFavoriteTour(Long id);
    List<FavoriteTourDTO> getFavoriteToursByUserId(Long userId);
    List<FavTourResponse> getFavoriteToursResponseByUserId(Long userId);
}