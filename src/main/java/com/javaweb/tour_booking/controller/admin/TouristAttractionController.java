package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.TouristAttractionDTO;
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

    //POST API cho create
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TouristAttractionDTO>> createTouristAttraction(@RequestBody TouristAttractionDTO attractionDTO) {
        TouristAttractionDTO result = touristAttractionService.createTouristAttraction(attractionDTO);
        ApiResponse<TouristAttractionDTO> response = new ApiResponse<>("Thêm địa điểm du lịch thành công", result);
        return ResponseEntity.ok(response);
    }

    //GET API cho getAll
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<TouristAttractionDTO>>> getTouristAttractions() {
        List<TouristAttractionDTO> touristAttractionDTOList = touristAttractionService.getAllTouristAttractions();
        ApiResponse<List<TouristAttractionDTO>> response = new ApiResponse<>("Lấy danh sách địa điểm du lịch thành công", touristAttractionDTOList);
        return ResponseEntity.ok(response);
    }

    //GET API cho getById
    @PostMapping("/get/{id}")
    public ResponseEntity<ApiResponse<TouristAttractionDTO>> getTouristAttractionById(@PathVariable Long id) {
        TouristAttractionDTO touristAttractionDTO =  touristAttractionService.getTouristAttractionById(id);
        ApiResponse<TouristAttractionDTO> response = new ApiResponse<>("Đã tìm thấy địa điểm", touristAttractionDTO);
        return ResponseEntity.ok(response);
    }

    //POST API cho update
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TouristAttractionDTO>> updateTouristAttraction(@PathVariable Long id, @RequestBody TouristAttractionDTO attractionDTO) {
        TouristAttractionDTO touristAttractionDTO = touristAttractionService.updateTouristAttraction(id, attractionDTO);
        ApiResponse<TouristAttractionDTO> response = new ApiResponse<>("Đã cập nhật thông tin địa điềm thành công", touristAttractionDTO);
        return ResponseEntity.ok(response);
    }

    //POST API cho delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTouristAttraction(@PathVariable Long id) {
        touristAttractionService.deleteTouristAttraction(id);
        ApiResponse<String> response = new ApiResponse<>("Đã cập nhật thông tin địa điềm thành công", null);
        return ResponseEntity.ok(response);
    }

}
