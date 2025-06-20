package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.UserMemberDTO;
import com.javaweb.tour_booking.service.IUserMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/user-member")
@AllArgsConstructor
public class UserMemberController {
    private final IUserMemberService userMemberService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<UserMemberDTO>>> getAllUserMembers() {
        List<UserMemberDTO> members = userMemberService.getAllUserMembers();
        ApiResponse<List<UserMemberDTO>> response = new ApiResponse<>("Fetched user members successfully", members);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<UserMemberDTO>> getUserMemberById(@PathVariable Long id) {
        UserMemberDTO member = userMemberService.getUserMemberById(id);
        ApiResponse<UserMemberDTO> response = new ApiResponse<>("User member found", member);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserMemberDTO>> createUserMember(@RequestBody UserMemberDTO userMemberDTO) {
        UserMemberDTO created = userMemberService.createUserMember(userMemberDTO);
        ApiResponse<UserMemberDTO> response = new ApiResponse<>("User member created successfully", created);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UserMemberDTO>> updateUserMember(@PathVariable Long id, @RequestBody UserMemberDTO userMemberDTO) {
        UserMemberDTO updated = userMemberService.updateUserMember(id, userMemberDTO);
        ApiResponse<UserMemberDTO> response = new ApiResponse<>("User member updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUserMember(@PathVariable Long id) {
        userMemberService.deleteUserMember(id);
        ApiResponse<String> response = new ApiResponse<>("User member deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<UserMemberDTO>>> getUserMembersByUserId(@PathVariable Long userId) {
        List<UserMemberDTO> members = userMemberService.getUserMembersByUserId(userId);
        ApiResponse<List<UserMemberDTO>> response = new ApiResponse<>("Fetched user members by userId successfully", members);
        return ResponseEntity.ok(response);
    }
}