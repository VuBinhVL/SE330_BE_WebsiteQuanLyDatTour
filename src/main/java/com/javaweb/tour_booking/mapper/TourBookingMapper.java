package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.entity.Invoice;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourBooking;
import com.javaweb.tour_booking.entity.User;

public class TourBookingMapper {
    public static TourBookingDTO mapToTourBookingDTO(TourBooking booking) {
        TourBookingDTO dto = new TourBookingDTO();
        dto.setId(booking.getId());
        dto.setSeatsBooked(booking.getSeatsBooked());
        dto.setTotalPrice(booking.getTotalPrice());
        dto.setStatus(booking.getStatus());
        dto.setCreatedAt(booking.getCreatedAt());
        dto.setUpdatedAt(booking.getUpdatedAt());
        dto.setTourId(booking.getTour().getId());
        dto.setUserId(booking.getUser().getId());
        dto.setInvoiceId(booking.getInvoice() != null ? booking.getInvoice().getId() : null);
        return dto;
    }

    public static TourBooking mapToTourBooking(TourBookingDTO dto, Tour tour, User user, Invoice invoice) {
        TourBooking booking = new TourBooking();
        booking.setId(dto.getId());
        booking.setSeatsBooked(dto.getSeatsBooked());
        booking.setTotalPrice(dto.getTotalPrice());
        booking.setStatus(dto.getStatus());
        booking.setCreatedAt(dto.getCreatedAt());
        booking.setUpdatedAt(dto.getUpdatedAt());
        booking.setTour(tour);
        booking.setUser(user);
        booking.setInvoice(invoice);
        return booking;
    }
}
