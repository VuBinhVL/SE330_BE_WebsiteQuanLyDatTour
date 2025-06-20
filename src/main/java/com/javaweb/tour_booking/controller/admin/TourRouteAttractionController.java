package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.response.TourRouteAttractionResponse;
import com.javaweb.tour_booking.service.ITourRouteAttractionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/tour-route-attraction")
@AllArgsConstructor
public class TourRouteAttractionController {
    private final ITourRouteAttractionService tourRouteAttractionService;

    // Get all tour route attractions by tourRouteId
    @GetMapping("/tour-route/{tourRouteId}")
    public ResponseEntity<ApiResponse<List<TourRouteAttractionResponse>>> getAllTourRouteAttractionsByTourRouteId(@PathVariable Long tourRouteId) {
        List<TourRouteAttractionResponse> attractions = tourRouteAttractionService.GetAllTourRouteAttractionsByTourRouteId(tourRouteId);
        ApiResponse<List<TourRouteAttractionResponse>> response = new ApiResponse<>("Lấy danh sách lịch trình theo tuyến du lịch thành công", attractions);
        return ResponseEntity.ok(response);
    }
}