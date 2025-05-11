package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.PopularTourRouteDTO;
import com.javaweb.tour_booking.service.IPopularTourRouteHomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/popular-tour-route")
@AllArgsConstructor
public class PopularTourRouteHomeController {
    private final IPopularTourRouteHomeService popularTourRouteHomeService;

    // GET API for getAllPopularTourRoutes
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPopularTourRoutes() {
        try {
            List<PopularTourRouteDTO> routes = popularTourRouteHomeService.getAllPopularTourRoutes();
            return ResponseEntity.ok(new ApiResponse<>("Lấy danh sách thành công", routes));
        } catch (Exception e) {
            e.printStackTrace();  // In ra lỗi cụ thể trong console
            return ResponseEntity.internalServerError().body(new ApiResponse<>("Lỗi server: " + e.getMessage(), null));
        }
    }

}