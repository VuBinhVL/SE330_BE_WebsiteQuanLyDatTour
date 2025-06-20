package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.TouristAttractionDTO;
import com.javaweb.tour_booking.dto.request.TouristAttractionUpdateRequest;
import com.javaweb.tour_booking.dto.response.TouristAttractionDetailResponse;
import com.javaweb.tour_booking.dto.response.TouristAttractionResponse;
import com.javaweb.tour_booking.entity.TouristAttraction;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.service.ITouristAttractionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> deleteTouristAttractionById(@PathVariable Long id){
        try {
            touristAttractionService.deleteTouristAttractionById(id);
            return ResponseEntity.ok(Map.of("message", "Xóa địa điểm thành công"));
        }
        catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", e.getMessage()));
        }

    }

    @PostMapping()
    public ResponseEntity<?> createTouristAttraction(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("images") List<MultipartFile> images
    ) {
        try {
            touristAttractionService.createTouristAttraction(name, location, description, categoryId, images);
            return ResponseEntity.ok().body(Map.of("message", "Thêm địa điểm thành công"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Thêm địa điểm thất bại", "error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTouristAttraction(@PathVariable Long id, @RequestBody TouristAttractionUpdateRequest request){
        try {
            touristAttractionService.updateTouristAttraction(id, request);
            return ResponseEntity.ok(Map.of("message", "Cập nhật thành công"));
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Cập nhật thất bại"));
        }
    }
}
