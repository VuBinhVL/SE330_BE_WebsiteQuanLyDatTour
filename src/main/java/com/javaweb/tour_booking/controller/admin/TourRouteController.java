package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.TourRouteDTO;
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

    // POST API for create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TourRouteDTO>> createTourRoute(@RequestBody TourRouteDTO tourRouteDTO) {
        TourRouteDTO result = tourRouteService.createTourRoute(tourRouteDTO);
        ApiResponse<TourRouteDTO> response = new ApiResponse<>("Thêm tuyến du lịch thành công", result);
        return ResponseEntity.ok(response);
    }

    // GET API for getAll
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<TourRouteDTO>>> getAllTourRoutes() {
        List<TourRouteDTO> tourRouteDTOList = tourRouteService.getAllTourRoutes();
        ApiResponse<List<TourRouteDTO>> response = new ApiResponse<>("Lấy danh sách tuyến du lịch thành công", tourRouteDTOList);
        return ResponseEntity.ok(response);
    }

    // POST API for getById
    @PostMapping("/get/{id}")
    public ResponseEntity<ApiResponse<TourRouteDTO>> getTourRouteById(@PathVariable Long id) {
        TourRouteDTO tourRouteDTO = tourRouteService.getTourRouteById(id);
        ApiResponse<TourRouteDTO> response = new ApiResponse<>("Đã tìm thấy tuyến du lịch", tourRouteDTO);
        return ResponseEntity.ok(response);
    }

    // PUT API for update
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TourRouteDTO>> updateTourRoute(@PathVariable Long id, @RequestBody TourRouteDTO tourRouteDTO) {
        TourRouteDTO updatedTourRouteDTO = tourRouteService.updateTourRoute(id, tourRouteDTO);
        ApiResponse<TourRouteDTO> response = new ApiResponse<>("Đã cập nhật thông tin tuyến du lịch thành công", updatedTourRouteDTO);
        return ResponseEntity.ok(response);
    }

    // DELETE API for delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTourRoute(@PathVariable Long id) {
        tourRouteService.deleteTourRoute(id);
        ApiResponse<String> response = new ApiResponse<>("Đã xóa tuyến du lịch thành công", null);
        return ResponseEntity.ok(response);
    }
}