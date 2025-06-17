package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.TouristAttractionDTO;
import com.javaweb.tour_booking.dto.response.TouristAttractionResponse;
import com.javaweb.tour_booking.service.ITouristAttractionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/tourist-attraction")
@AllArgsConstructor
public class TouristAttractionController {
    private ITouristAttractionService touristAttractionService;

    @GetMapping
    public ResponseEntity<List<TouristAttractionResponse>> getAllTouristAttractions(){
        return new ResponseEntity(touristAttractionService.getAllTouristAttractions(), HttpStatus.OK);
    }
}
