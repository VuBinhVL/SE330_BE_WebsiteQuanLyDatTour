package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.dto.response.HistoryUserBooking;
import com.javaweb.tour_booking.entity.Invoice;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourBooking;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.exception.user.UserNotFoundException;
import com.javaweb.tour_booking.mapper.TourBookingMapper;
import com.javaweb.tour_booking.repository.InvoiceRepository;
import com.javaweb.tour_booking.repository.TourBookingRepository;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.repository.UserRepository;
import com.javaweb.tour_booking.service.ITourBookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourBookingServiceImpl implements ITourBookingService {

    private final TourBookingRepository tourBookingRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public TourBookingDTO createTourBooking(TourBookingDTO dto) {
        Tour tour = tourRepository.findById(dto.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Invoice invoice = null;
        if (dto.getInvoiceId() != null) {
            invoice = invoiceRepository.findById(dto.getInvoiceId())
                    .orElseThrow(() -> new RuntimeException("Invoice not found"));
        }
        TourBooking booking = TourBookingMapper.mapToTourBooking(dto, tour, user, invoice);
        TourBooking saved = tourBookingRepository.save(booking);
        return TourBookingMapper.mapToTourBookingDTO(saved);
    }

    @Override
    public TourBookingDTO getTourBookingById(Long id) {
        TourBooking booking = tourBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TourBooking not found"));
        return TourBookingMapper.mapToTourBookingDTO(booking);
    }

    @Override
    public TourBookingDTO updateTourBooking(Long id, TourBookingDTO dto) {
        TourBooking booking = tourBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TourBooking not found"));
        Tour tour = tourRepository.findById(dto.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Invoice invoice = null;
        if (dto.getInvoiceId() != null) {
            invoice = invoiceRepository.findById(dto.getInvoiceId())
                    .orElseThrow(() -> new RuntimeException("Invoice not found"));
        }
        booking.setSeatsBooked(dto.getSeatsBooked());
        booking.setTotalPrice(dto.getTotalPrice());
        booking.setCreatedAt(dto.getCreatedAt());
        booking.setUpdatedAt(dto.getUpdatedAt());
        booking.setTour(tour);
        booking.setUser(user);
        booking.setInvoice(invoice);
        TourBooking updated = tourBookingRepository.save(booking);
        return TourBookingMapper.mapToTourBookingDTO(updated);
    }

    @Override
    public void deleteTourBooking(Long id) {
        if (!tourBookingRepository.existsById(id)) {
            throw new RuntimeException("TourBooking not found");
        }
        tourBookingRepository.deleteById(id);
    }

    @Override
    public List<TourBookingDTO> getAllTourBookings() {
        return tourBookingRepository.findAll()
                .stream()
                .map(TourBookingMapper::mapToTourBookingDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoryUserBooking> getHistoryByUserId(Long userId) {
        return tourBookingRepository.findHistoryByUserId(userId);
    }
}