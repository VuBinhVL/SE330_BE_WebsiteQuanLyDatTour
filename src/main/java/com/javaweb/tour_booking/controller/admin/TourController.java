package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.service.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/tour")
@AllArgsConstructor
public class TourController {
    private final ITourService tourService;
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<TourDTO>>> getAllTours() {
        List<TourDTO> tours = tourService.getAllTours();
        ApiResponse<List<TourDTO>> response = new ApiResponse<>("Lấy danh sách chuyến du lịch thành công", tours);
        return ResponseEntity.ok(response);
    }
}
