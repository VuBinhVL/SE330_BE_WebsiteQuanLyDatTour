package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.service.ITourBookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/tour-booking")
@AllArgsConstructor
public class TourBookingController {

    private final ITourBookingService bookingService;

    // Lấy tất cả đơn đặt tour
    @GetMapping
    public ResponseEntity<List<TourBookingDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // Lấy đơn đặt tour theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TourBookingDTO> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    // Xóa đơn đặt tour theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.ok().build();
    }
}
