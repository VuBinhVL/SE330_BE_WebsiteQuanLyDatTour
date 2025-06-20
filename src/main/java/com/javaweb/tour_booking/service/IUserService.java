package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.UserDTO;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);

    // Add this method for updating user avatar
    String updateUserAvatar(Long userId, MultipartFile file);
}