package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.dto.response.HistoryUserBooking;
import com.javaweb.tour_booking.service.ITourBookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.javaweb.tour_booking.dto.response.HomeAdminBookingResponse;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/tour-booking")
@AllArgsConstructor
public class TourBookingController {
    private final ITourBookingService tourBookingService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<TourBookingDTO>>> getAllTourBookings() {
        List<TourBookingDTO> bookings = tourBookingService.getAllTourBookings();
        ApiResponse<List<TourBookingDTO>> response = new ApiResponse<>("Fetched booking list successfully", bookings);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<TourBookingDTO>> getTourBookingById(@PathVariable Long id) {
        TourBookingDTO booking = tourBookingService.getTourBookingById(id);
        ApiResponse<TourBookingDTO> response = new ApiResponse<>("Booking found", booking);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TourBookingDTO>> createTourBooking(@RequestBody TourBookingDTO dto) {
        TourBookingDTO created = tourBookingService.createTourBooking(dto);
        ApiResponse<TourBookingDTO> response = new ApiResponse<>("Booking created successfully", created);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TourBookingDTO>> updateTourBooking(@PathVariable Long id, @RequestBody TourBookingDTO dto) {
        TourBookingDTO updated = tourBookingService.updateTourBooking(id, dto);
        ApiResponse<TourBookingDTO> response = new ApiResponse<>("Booking updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTourBooking(@PathVariable Long id) {
        tourBookingService.deleteTourBooking(id);
        ApiResponse<String> response = new ApiResponse<>("Booking deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<ApiResponse<List<HistoryUserBooking>>> getHistoryByUserId(@PathVariable Long userId) {
        List<HistoryUserBooking> history = tourBookingService.getHistoryByUserId(userId);
        ApiResponse<List<HistoryUserBooking>> response = new ApiResponse<>("Fetched booking history successfully", history);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin-home-bookings")
    public ResponseEntity<ApiResponse<List<HomeAdminBookingResponse>>> getAllHomeAdminBookings() {
        List<HomeAdminBookingResponse> bookings = tourBookingService.getAllHomeAdminBookings();
        ApiResponse<List<HomeAdminBookingResponse>> response = new ApiResponse<>("Fetched admin home bookings successfully", bookings);
        return ResponseEntity.ok(response);
    }
}