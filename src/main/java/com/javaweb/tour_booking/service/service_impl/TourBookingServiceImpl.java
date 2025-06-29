package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.dto.response.HistoryUserBooking;
import com.javaweb.tour_booking.dto.response.HomeAdminBookingResponse;
import com.javaweb.tour_booking.dto.response.TourBookingDetailResponse;
import com.javaweb.tour_booking.entity.*;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.exception.user.UserNotFoundException;
import com.javaweb.tour_booking.mapper.TourBookingMapper;
import com.javaweb.tour_booking.repository.*;
import com.javaweb.tour_booking.service.ITourBookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

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
        if (tour.getStatus() == 1) {
            throw new IllegalArgumentException("Tour đã hủy, không thể tạo phiếu");
        }
        if (tour.getBookedSeats() == tour.getTotalSeats()) {
            throw new IllegalArgumentException("Tour đã đầy, không thể tạo phiếu");
        }
        if (dto.getSeatsBooked() + tour.getBookedSeats() > tour.getTotalSeats()) {
            throw new IllegalArgumentException("Số phiếu đặt vượt giới hạn số lượng khách tối đa, không thể tạo phiếu");
        }
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Invoice invoice = null;
        if (dto.getInvoiceId() != null) {
            invoice = invoiceRepository.findById(dto.getInvoiceId())
                    .orElseThrow(() -> new RuntimeException("Invoice not found"));
        }
        TourBooking booking = TourBookingMapper.mapToTourBooking(dto, tour, user, invoice, true);
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

    @Override
    public List<HomeAdminBookingResponse> getAllHomeAdminBookings() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        return tourBookingRepository.findAll().stream()
                .filter(booking -> booking.getCreatedAt() != null && booking.getCreatedAt().isAfter(oneMonthAgo))
                .map(booking -> {
                    Tour tour = booking.getTour();
                    TourRoute route = (tour != null) ? tour.getTourRoute() : null;
                    String routeName = (route != null) ? route.getRouteName() : null;
                    String image = (route != null) ? route.getImage() : null;
                    int quantity = booking.getSeatsBooked();
                    boolean status = booking.getInvoice() != null && Boolean.TRUE.equals(booking.getInvoice().getPaymentStatus());
                    LocalDateTime createdAt = booking.getCreatedAt();
                    return new HomeAdminBookingResponse(
                            booking.getId(),
                            routeName,
                            image,
                            quantity,
                            status,
                            createdAt
                    );
                })
                .collect(Collectors.toList());
    }
}