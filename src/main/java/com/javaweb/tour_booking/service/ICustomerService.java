package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.UserDTO;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ICustomerService {
    // Get all customers (users with role id = 1)
    List<UserDTO> getAllCustomers();

    // Create a new customer
    UserDTO createCustomer(UserDTO userDTO);

    // Get customer by id
    UserDTO getCustomerById(Long id);

    // Update customer information
    UserDTO updateCustomer(Long id, UserDTO userDTO);

    // Delete customer
    void deleteCustomer(Long id);
    String updateCustomerAvatar(Long customerId, MultipartFile file);
}