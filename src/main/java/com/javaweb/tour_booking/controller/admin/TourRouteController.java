package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.FavoriteRouteDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.dto.response.TourRouteSearchResponse;
import com.javaweb.tour_booking.service.ITourRouteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/tour-route")
@AllArgsConstructor
public class TourRouteController {
    private final ITourRouteService tourRouteService;

    // Create a new tour route
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TourRouteDTO>> createTourRoute(@RequestBody TourRouteDTO tourRouteDTO) {
        TourRouteDTO result = tourRouteService.createTourRoute(tourRouteDTO);
        ApiResponse<TourRouteDTO> response = new ApiResponse<>("Thêm tuyến du lịch thành công", result);
        return ResponseEntity.ok(response);
    }

    // Get all tour routes
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<TourRouteDTO>>> getAllTourRoutes() {
        List<TourRouteDTO> tourRoutes = tourRouteService.getAllTourRoutes();
        ApiResponse<List<TourRouteDTO>> response = new ApiResponse<>("Lấy danh sách tuyến du lịch thành công", tourRoutes);
        return ResponseEntity.ok(response);
    }

    // Get a tour route by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<TourRouteDTO>> getTourRouteById(@PathVariable Long id) {
        TourRouteDTO tourRouteDTO = tourRouteService.getTourRouteById(id);
        ApiResponse<TourRouteDTO> response = new ApiResponse<>("Đã tìm thấy tuyến du lịch", tourRouteDTO);
        return ResponseEntity.ok(response);
    }

    // Update a tour route
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TourRouteDTO>> updateTourRoute(@PathVariable Long id, @RequestBody TourRouteDTO tourRouteDTO) {
        TourRouteDTO updated = tourRouteService.updateTourRoute(id, tourRouteDTO);
        ApiResponse<TourRouteDTO> response = new ApiResponse<>("Đã cập nhật thông tin tuyến du lịch thành công", updated);
        return ResponseEntity.ok(response);
    }

    // Delete a tour route
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTourRoute(@PathVariable Long id) {
        tourRouteService.deleteTourRoute(id);
        ApiResponse<String> response = new ApiResponse<>("Đã xóa tuyến du lịch thành công", null);
        return ResponseEntity.ok(response);
    }

    // Get top 4 favorite routes
    @GetMapping("/get-top4-favorite")
    public ResponseEntity<ApiResponse<List<FavoriteRouteDTO>>> getTop4FavoriteRoutes() {
        List<FavoriteRouteDTO> favoriteRoutes = tourRouteService.getTop4FavoriteRoutes();
        ApiResponse<List<FavoriteRouteDTO>> response = new ApiResponse<>("Lấy top 4 tuyến du lịch yêu thích thành công", favoriteRoutes);
        return ResponseEntity.ok(response);
    }

    // Search tour routes with custom response
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<TourRouteSearchResponse>>> searchTourRoutes() {
        List<TourRouteSearchResponse> result = tourRouteService.searchTourRoutes();
        ApiResponse<List<TourRouteSearchResponse>> response =
                new ApiResponse<>("Lấy danh sách tuyến du lịch theo điều kiện thành công", result);
        return ResponseEntity.ok(response);
    }
}