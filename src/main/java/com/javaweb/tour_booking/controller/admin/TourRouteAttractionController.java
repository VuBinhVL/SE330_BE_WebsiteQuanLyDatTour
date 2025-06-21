package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.TourRouteAttractionDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;
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
    // Create a new tour route attraction
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TourRouteAttractionDTO>> createTourRouteAttraction(@RequestBody TourRouteAttractionDTO newTourRouteAttraction) {
        TourRouteAttractionDTO createdAttraction = tourRouteAttractionService.CreateTourRouteAttractionByTourRouteId(newTourRouteAttraction);
        ApiResponse<TourRouteAttractionDTO> response = new ApiResponse<>("Tạo lịch trình theo tuyến du lịch thành công", createdAttraction);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{tourRouteAttractionId}")
    public ResponseEntity<ApiResponse<TourRouteAttractionDTO>> updateTourRouteAttraction(@PathVariable Long tourRouteAttractionId, @RequestBody TourRouteAttractionDTO updatedTourRouteAttraction) {
        TourRouteAttractionDTO updatedAttraction = tourRouteAttractionService.UpdateTourRouteAttractionByTourRouteId(tourRouteAttractionId, updatedTourRouteAttraction);
        ApiResponse<TourRouteAttractionDTO> response = new ApiResponse<>("Cập nhật lịch trình theo tuyến du lịch thành công", updatedAttraction);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTour(@PathVariable Long id) {
        tourRouteAttractionService.DeleteTourRouteAttraction(id);
        ApiResponse<String> response = new ApiResponse<>("Đã xóa lịch trình thành công", null);
        return ResponseEntity.ok(response);
    }
}