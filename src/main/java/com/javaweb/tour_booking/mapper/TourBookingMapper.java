package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.entity.Invoice;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourBooking;
import com.javaweb.tour_booking.entity.User;

import java.time.LocalDateTime;

public class TourBookingMapper {
    public static TourBookingDTO mapToTourBookingDTO(TourBooking booking) {
        TourBookingDTO dto = new TourBookingDTO();
        dto.setId(booking.getId());
        dto.setSeatsBooked(booking.getSeatsBooked());
        dto.setTotalPrice(booking.getTotalPrice());
        dto.setCreatedAt(booking.getCreatedAt());
        dto.setUpdatedAt(booking.getUpdatedAt());
        dto.setTourId(booking.getTour().getId());
        dto.setUserId(booking.getUser().getId());
        dto.setTourStatus(booking.getTour().getStatus());
        dto.setInvoiceId(booking.getInvoice() != null ? booking.getInvoice().getId() : null);
        // map user
        dto.setUser(UserMapper.mapToUserDTO(booking.getUser()));

        // map tour
        dto.setTour(TourMapper.mapToTourDTO(booking.getTour()));

        // map tourRoute (nested from tour)
        if (booking.getTour().getTourRoute() != null) {
            dto.setTourRoute(TourRouteMapper.mapToTourRouteDTO(booking.getTour().getTourRoute()));
        }
        return dto;
    }

    public static TourBooking mapToTourBooking(TourBookingDTO dto, Tour tour, User user, Invoice invoice, boolean isCreate) {
        TourBooking booking = new TourBooking();
        booking.setId(dto.getId());
        booking.setSeatsBooked(dto.getSeatsBooked());
        booking.setTotalPrice(dto.getTotalPrice());
        booking.setTour(tour);
        booking.setUser(user);
        booking.setInvoice(invoice);

        if (isCreate) {
            booking.setCreatedAt(LocalDateTime.now());
            booking.setUpdatedAt(null);
        } else {
            booking.setCreatedAt(dto.getCreatedAt());
            booking.setUpdatedAt(LocalDateTime.now());
        }

        return booking;
    }
}
