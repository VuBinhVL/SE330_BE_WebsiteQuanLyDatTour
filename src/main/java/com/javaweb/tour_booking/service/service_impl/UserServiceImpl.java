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
import com.javaweb.tour_booking.service.FileStorageService;
import com.javaweb.tour_booking.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final FileStorageService fileStorageService;

    @Override
    public String updateUserAvatar(Long userId, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        try {
            String oldAvatarPath = user.getAvatar();
            // Only try to delete if it's a local file (not a URL)
            if (oldAvatarPath != null && !oldAvatarPath.isEmpty() && !oldAvatarPath.startsWith("http")) {
                try {
                    String relativePath = oldAvatarPath.startsWith("/") ? oldAvatarPath.substring(1) : oldAvatarPath;
                    java.nio.file.Path filePath = java.nio.file.Paths.get(relativePath).toAbsolutePath();
                    java.io.File oldAvatarFile = filePath.toFile();
                    if (oldAvatarFile.exists()) {
                        oldAvatarFile.delete();
                    }
                } catch (Exception ignored) {
                    // Ignore any error when deleting old avatar
                }
            }

            // Save new avatar
            String avatarPath = fileStorageService.saveOrUpdateAvatar(userId, file);
            user.setAvatar(avatarPath);
            userRepository.save(user);
            return avatarPath;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload avatar", e);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Role role = roleRepository.findById(userDTO.getRole_id())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Account account = accountRepository.findById(userDTO.getAccount_id())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        User user = UserMapper.mapToUser(userDTO, role, account);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBirthday(userDTO.getBirthday());
        user.setAddress(userDTO.getAddress());
        user.setSex(userDTO.getSex());
        user.setAvatar(userDTO.getAvatar());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        // Update role and account if needed
        if (userDTO.getRole_id() != null && !userDTO.getRole_id().equals(user.getRole().getId())) {
            Role role = roleRepository.findById(userDTO.getRole_id())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);
        }
        if (userDTO.getAccount_id() != null && !userDTO.getAccount_id().equals(user.getAccount().getId())) {
            Account account = accountRepository.findById(userDTO.getAccount_id())
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            user.setAccount(account);
        }
        User updatedUser = userRepository.save(user);
        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}