package com.javaweb.tour_booking.controller.customer;

import com.javaweb.tour_booking.dto.response.TourRouteDetailResponse;
import com.javaweb.tour_booking.service.ITourRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tour-route")
@RequiredArgsConstructor
public class TourRouteCustomerController {
    private final ITourRouteService tourRouteService;

    @GetMapping("/{id}")
    public ResponseEntity<TourRouteDetailResponse> getDetail(
            @PathVariable Long id,
            @RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(tourRouteService.getDetail(id, userId));
    }
}
