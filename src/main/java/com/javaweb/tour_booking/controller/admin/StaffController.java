package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.UserDTO;
import com.javaweb.tour_booking.service.IStaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/staff")
@AllArgsConstructor
public class StaffController {

    private final IStaffService staffService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserDTO>> createStaff(@RequestBody UserDTO userDTO) {
        UserDTO result = staffService.createStaff(userDTO);
        ApiResponse<UserDTO> response = new ApiResponse<>("Staff created successfully", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllStaff() {
        List<UserDTO> staffList = staffService.getAllStaff();
        ApiResponse<List<UserDTO>> response = new ApiResponse<>("Fetched staff list successfully", staffList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getStaffById(@PathVariable Long id) {
        UserDTO staff = staffService.getStaffById(id);
        ApiResponse<UserDTO> response = new ApiResponse<>("Staff found", staff);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateStaff(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updated = staffService.updateStaff(id, userDTO);
        ApiResponse<UserDTO> response = new ApiResponse<>("Staff updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        ApiResponse<String> response = new ApiResponse<>("Staff deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-avatar/{id}")
    public ResponseEntity<ApiResponse<String>> updateStaffAvatar(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        String avatarPath = staffService.updateStaffAvatar(id, file);
        ApiResponse<String> response = new ApiResponse<>("Avatar updated successfully", avatarPath);
        return ResponseEntity.ok(response);
    }
}
