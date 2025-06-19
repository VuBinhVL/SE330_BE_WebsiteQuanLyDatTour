package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.service.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/tour")
@AllArgsConstructor
public class TourController {
    private final ITourService tourService;
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<TourDTO>>> getAllTours() {
        List<TourDTO> tours = tourService.GetAllTours();
        ApiResponse<List<TourDTO>> response = new ApiResponse<>("Lấy danh sách chuyến du lịch thành công", tours);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<TourDTO>> getTourById(@PathVariable Long id) {
        TourDTO tourDTO = tourService.GetTourById(id);
        ApiResponse<TourDTO> response = new ApiResponse<>("Đã tìm thấy chuyến du lịch", tourDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TourDTO>> createTour(@RequestBody TourDTO tourDTO) {
        TourDTO result = tourService.CreateTour(tourDTO);
        ApiResponse<TourDTO> response = new ApiResponse<>("Thêm tour thành công", result);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TourDTO>> updateTour(@PathVariable Long id, @RequestBody TourDTO tourDTO) {
        TourDTO updated = tourService.UpdateTour(id, tourDTO);
        ApiResponse<TourDTO> response = new ApiResponse<>("Đã cập nhật thông tin chuyến du lịch thành công", updated);
        return ResponseEntity.ok(response);
    }
}
