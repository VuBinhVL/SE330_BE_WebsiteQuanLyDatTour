
package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.UserDTO;
import com.javaweb.tour_booking.entity.Account;
import com.javaweb.tour_booking.entity.Role;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.exception.user.UserNotFoundException;
import com.javaweb.tour_booking.mapper.UserMapper;
import com.javaweb.tour_booking.repository.AccountRepository;
import com.javaweb.tour_booking.repository.RoleRepository;
import com.javaweb.tour_booking.repository.UserRepository;
import com.javaweb.tour_booking.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.javaweb.tour_booking.service.FileStorageService;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final FileStorageService fileStorageService;

    @Override
    public String updateCustomerAvatar(Long customerId, MultipartFile file) {
        User user = userRepository.findByIdAndRoleId(customerId, 2L)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));
        try {
            // Delete old avatar if exists
            String oldAvatarPath = user.getAvatar();
            if (oldAvatarPath != null && !oldAvatarPath.isEmpty()) {
                String relativePath = oldAvatarPath.startsWith("/") ? oldAvatarPath.substring(1) : oldAvatarPath;
                java.nio.file.Path filePath = java.nio.file.Paths.get(relativePath).toAbsolutePath();
                java.io.File oldAvatarFile = filePath.toFile();
                if (oldAvatarFile.exists()) {
                    oldAvatarFile.delete();
                }
            }

            // Save new avatar
            String avatarPath = fileStorageService.saveOrUpdateAvatar(customerId, file);
            user.setAvatar(avatarPath);
            userRepository.save(user);
            return avatarPath;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload avatar", e);
        }
    }

    @Override
    public List<UserDTO> getAllCustomers() {
        // Find all users with role id = 1
        List<User> customers = userRepository.findAllByRoleId(1L);
        return customers.stream()
                .map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createCustomer(UserDTO userDTO) {
        Role role = roleRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Account account = accountRepository.findById(userDTO.getAccount_id())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        User user = UserMapper.mapToUser(userDTO, role, account);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO getCustomerById(Long id) {
        User user = userRepository.findByIdAndRoleId(id, 2L)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public UserDTO updateCustomer(Long id, UserDTO userDTO) {
        User user = userRepository.findByIdAndRoleId(id, 2L)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBirthday(userDTO.getBirthday());
        user.setAddress(userDTO.getAddress());
        user.setSex(userDTO.getSex());
        user.setAvatar(userDTO.getAvatar());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        User updatedUser = userRepository.save(user);
        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public void deleteCustomer(Long id) {
        User user = userRepository.findByIdAndRoleId(id, 2L)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));

        // Delete avatar file if exists
        String avatarPath = user.getAvatar();
        if (avatarPath != null && !avatarPath.isEmpty()) {
            // Remove leading slash if present
            String relativePath = avatarPath.startsWith("/") ? avatarPath.substring(1) : avatarPath;
            java.nio.file.Path filePath = java.nio.file.Paths.get(relativePath).toAbsolutePath();
            java.io.File avatarFile = filePath.toFile();
            if (avatarFile.exists()) {
                avatarFile.delete();
            }
        }

        userRepository.deleteById(user.getId());
    }
}