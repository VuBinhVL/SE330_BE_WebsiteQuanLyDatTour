package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourRoute;

public class TourMapper {
    public static TourDTO mapToTourDTO(Tour tour) {
        TourDTO tourDTO = new TourDTO();
        tourDTO.setId(tour.getId());
        tourDTO.setDepatureDate(tour.getDepatureDate());
        tourDTO.setReturnDate(tour.getReturnDate());
        tourDTO.setPickUpLocation(tour.getPickUpLocation());
        tourDTO.setPickUpLocation(tour.getPickUpLocation());
        tourDTO.setStatus(tour.getStatus());
        tourDTO.setPrice(tour.getPrice());
        tourDTO.setTotalSeats(tour.getTotalSeats());
        tourDTO.setBookedSeats(tour.getBookedSeats());
        tourDTO.setCreatedAt(tour.getCreatedAt());
        tourDTO.setUpdatedAt(tour.getUpdatedAt());
        tourDTO.setTourRoute(tour.getId());
        return tourDTO;
    }


    public static Tour mapToTour(TourDTO tourDTO, TourRoute tourRoute) {
        Tour tour = new Tour();
        tour.setId(tourDTO.getId());
        tour.setDepatureDate(tourDTO.getDepatureDate());
        tour.setReturnDate(tourDTO.getReturnDate());
        tour.setPickUpLocation(tourDTO.getPickUpLocation());
        tour.setPickUpLocation(tourDTO.getPickUpLocation());
        tour.setStatus(tourDTO.getStatus());
        tour.setPrice(tourDTO.getPrice());
        tour.setTotalSeats(tourDTO.getTotalSeats());
        tour.setBookedSeats(tourDTO.getBookedSeats());
        tour.setCreatedAt(tourDTO.getCreatedAt());
        tour.setUpdatedAt(tourDTO.getUpdatedAt());
        tour.setTourRoute(tourRoute);
        return tour;
    }
}
