package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.UserDTO;
import com.javaweb.tour_booking.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/customer")
@AllArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserDTO>> createCustomer(@RequestBody UserDTO userDTO) {
        UserDTO result = customerService.createCustomer(userDTO);
        ApiResponse<UserDTO> response = new ApiResponse<>("Customer created successfully", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllCustomers() {
        List<UserDTO> customers = customerService.getAllCustomers();
        ApiResponse<List<UserDTO>> response = new ApiResponse<>("Fetched customer list successfully", customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getCustomerById(@PathVariable Long id) {
        UserDTO customer = customerService.getCustomerById(id);
        ApiResponse<UserDTO> response = new ApiResponse<>("Customer found", customer);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateCustomer(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updated = customerService.updateCustomer(id, userDTO);
        ApiResponse<UserDTO> response = new ApiResponse<>("Customer updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        ApiResponse<String> response = new ApiResponse<>("Customer deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    // New endpoint for avatar upload/update
    @PostMapping("/update-avatar/{id}")
    public ResponseEntity<ApiResponse<String>> updateCustomerAvatar(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        String avatarPath = customerService.updateCustomerAvatar(id, file);
        ApiResponse<String> response = new ApiResponse<>("Avatar updated successfully", avatarPath);
        return ResponseEntity.ok(response);
    }
}