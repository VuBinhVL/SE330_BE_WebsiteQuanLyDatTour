package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.UserDTO;
import com.javaweb.tour_booking.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/user")
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        ApiResponse<List<UserDTO>> response = new ApiResponse<>("Fetched user list successfully", users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        ApiResponse<UserDTO> response = new ApiResponse<>("User found", user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody UserDTO userDTO) {
        UserDTO created = userService.createUser(userDTO);
        ApiResponse<UserDTO> response = new ApiResponse<>("User created successfully", created);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updated = userService.updateUser(id, userDTO);
        ApiResponse<UserDTO> response = new ApiResponse<>("User updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        ApiResponse<String> response = new ApiResponse<>("User deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-avatar/{id}")
    public ResponseEntity<ApiResponse<String>> updateUserAvatar(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        String avatarPath = userService.updateUserAvatar(id, file);
        ApiResponse<String> response = new ApiResponse<>("Avatar updated successfully", avatarPath);
        return ResponseEntity.ok(response);
    }
}