package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourRoute;

import java.time.LocalDateTime;

public class TourMapper {
    public static TourDTO mapToTourDTO(Tour tour) {
        if (tour == null) {
            throw new IllegalArgumentException("Tour không được null");
        }

        TourDTO tourDTO = new TourDTO();
        tourDTO.setId(tour.getId());
        tourDTO.setDepatureDate(tour.getDepatureDate());
        tourDTO.setReturnDate(tour.getReturnDate());
        tourDTO.setPickUpLocation(tour.getPickUpLocation());
        tourDTO.setPickUpTime(tour.getPickUpTime());
        tourDTO.setStatus(tour.getStatus());
        tourDTO.setPrice(tour.getPrice());
        tourDTO.setTotalSeats(tour.getTotalSeats());
        tourDTO.setBookedSeats(tour.getBookedSeats());
        tourDTO.setCreatedAt(tour.getCreatedAt());
        tourDTO.setUpdatedAt(tour.getUpdatedAt());
        if (tour.getTourRoute() != null) {
            tourDTO.setTourRouteId(tour.getTourRoute().getId());
        }

        return tourDTO;
    }

    public static Tour mapToTour(TourDTO tourDTO, TourRoute tourRoute) {
        if (tourDTO == null) {
            throw new IllegalArgumentException("TourDTO không được null");
        }
        if (tourRoute == null) {
            throw new IllegalArgumentException("TourRoute không được null");
        }

        Tour tour = new Tour();
        tour.setDepatureDate(tourDTO.getDepatureDate());
        tour.setReturnDate(tourDTO.getReturnDate());
        tour.setPickUpLocation(tourDTO.getPickUpLocation());
        tour.setPickUpTime(tourDTO.getPickUpTime());
        tour.setStatus(tourDTO.getStatus());
        tour.setPrice(tourDTO.getPrice());
        tour.setTotalSeats(tourDTO.getTotalSeats());
        tour.setBookedSeats(tourDTO.getBookedSeats());
        tour.setCreatedAt(LocalDateTime.now());
        tour.setUpdatedAt(LocalDateTime.now());
        tour.setTourRoute(tourRoute);

        return tour;
    }
}