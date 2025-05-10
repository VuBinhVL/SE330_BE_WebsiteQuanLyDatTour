package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.FavoriteTourDTO;
import com.javaweb.tour_booking.entity.FavoriteTour;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.entity.User;

public class FavotiteTourMapper {
    public static FavoriteTourDTO mapToFavoriteTourDTO(FavoriteTour favoriteTour) {
        FavoriteTourDTO favoriteTourDTO = new FavoriteTourDTO();
        favoriteTourDTO.setId(favoriteTour.getId());
        favoriteTourDTO.setCreatedAt(favoriteTour.getCreatedAt());
        favoriteTourDTO.setTourRouteId(favoriteTour.getTourRoute().getId());
        favoriteTourDTO.setUserID(favoriteTour.getUser().getId());
        return favoriteTourDTO;
    }

    public static FavoriteTour mapToFavoriteTour(FavoriteTourDTO favoriteTourDTO, TourRoute tourRoute, User user) {
        FavoriteTour favoriteTour = new FavoriteTour();
        favoriteTour.setId(favoriteTourDTO.getId());
        favoriteTour.setCreatedAt(favoriteTourDTO.getCreatedAt());
        favoriteTour.setUser(user);
        favoriteTour.setTourRoute(tourRoute);
        return favoriteTour;
    }
}
