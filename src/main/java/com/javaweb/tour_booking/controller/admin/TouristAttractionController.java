package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.TouristAttractionDTO;
import com.javaweb.tour_booking.dto.response.TouristAttractionDetailResponse;
import com.javaweb.tour_booking.dto.response.TouristAttractionResponse;
import com.javaweb.tour_booking.entity.TouristAttraction;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
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
        return new ResponseEntity<>(touristAttractionService.getAllTouristAttractions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<TouristAttractionDetailResponse> getTouristAttractionById(@PathVariable Long id){
        return new ResponseEntity<>(touristAttractionService.getTouristAttractionById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTouristAttractionById(@PathVariable Long id){
        try {
            touristAttractionService.deleteTouristAttractionById(id);
            return ResponseEntity.ok("Xóa địa điểm du lịch thành công");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
