package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.TourBookingDetailDTO;
import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.entity.TourBookingDetail;
import com.javaweb.tour_booking.service.ITourBookingDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/tour-booking-detail")
@AllArgsConstructor
public class TourBookingDetailController {
    private final ITourBookingDetailService tourBookingDetailService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TourBookingDetailDTO>> createTour(@RequestBody TourBookingDetailDTO tourBookingDetailDTO) {
        TourBookingDetailDTO result = tourBookingDetailService.CreateTourBookingDetail(tourBookingDetailDTO);
        ApiResponse<TourBookingDetailDTO> response = new ApiResponse<>("Thêm chi tiết tour thành công", result);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<TourBookingDetailDTO>>> getAllTourBookingDetails() {
        List<TourBookingDetailDTO> result = tourBookingDetailService.getAllTourBookingDetails();
        ApiResponse<List<TourBookingDetailDTO>> response = new ApiResponse<>("Lấy danh sách chi tiết tour thành công", result);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get/{bookingId}")
    public ResponseEntity<ApiResponse<List<TourBookingDetailDTO>>> getByBookingId(@PathVariable Long bookingId) {
        List<TourBookingDetailDTO> result = tourBookingDetailService.getByBookingId(bookingId);
        return ResponseEntity.ok(new ApiResponse<>("Lấy chi tiết tour theo booking thành công", result));
    }

}
