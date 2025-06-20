package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.entity.TourBooking;
import com.javaweb.tour_booking.mapper.TourBookingMapper;
import com.javaweb.tour_booking.repository.TourBookingRepository;
import com.javaweb.tour_booking.service.ITourBookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourBookingServiceImpl implements ITourBookingService {

    private final TourBookingRepository bookingRepository;

    @Override
    public List<TourBookingDTO> getAllBookings() {
        List<TourBooking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(TourBookingMapper::mapToTourBookingDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TourBookingDTO getBookingById(Long id) {
        TourBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy booking với ID: " + id));
        return TourBookingMapper.mapToTourBookingDTO(booking);
    }

    @Override
    public void deleteBookingById(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy booking để xóa với ID: " + id);
        }
        bookingRepository.deleteById(id);
    }
}
