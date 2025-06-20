package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IStaffService {
    // Get all staff (users with role id = 2 or role = "STAFF")
    List<UserDTO> getAllStaff();

    // Create a new staff
    UserDTO createStaff(UserDTO userDTO);

    // Get staff by id
    UserDTO getStaffById(Long id);

    // Update staff information
    UserDTO updateStaff(Long id, UserDTO userDTO);

    // Delete staff
    void deleteStaff(Long id);

    // Update staff avatar
    String updateStaffAvatar(Long staffId, MultipartFile file);
}
