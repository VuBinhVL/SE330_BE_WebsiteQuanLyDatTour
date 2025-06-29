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
import com.javaweb.tour_booking.service.IStaffService;
import com.javaweb.tour_booking.service.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StaffServiceImpl implements IStaffService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final FileStorageService fileStorageService;

    private final Long STAFF_ROLE_ID = 3L;

    @Override
    public String updateStaffAvatar(Long staffId, MultipartFile file) {
        User user = userRepository.findByIdAndRoleId(staffId, STAFF_ROLE_ID)
                .orElseThrow(() -> new UserNotFoundException("Staff not found"));
        try {
            String oldAvatarPath = user.getAvatar();
            if (oldAvatarPath != null && !oldAvatarPath.isEmpty()) {
                String relativePath = oldAvatarPath.startsWith("/") ? oldAvatarPath.substring(1) : oldAvatarPath;
                java.nio.file.Path filePath = java.nio.file.Paths.get(relativePath).toAbsolutePath();
                java.io.File oldAvatarFile = filePath.toFile();
                if (oldAvatarFile.exists()) {
                    oldAvatarFile.delete();
                }
            }

            String avatarPath = fileStorageService.saveOrUpdateAvatar(staffId, file);
            user.setAvatar(avatarPath);
            userRepository.save(user);
            return avatarPath;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload avatar", e);
        }
    }

    @Override
    public List<UserDTO> getAllStaff() {
        List<User> staffList = userRepository.findAllByRoleId(STAFF_ROLE_ID);
        return staffList.stream()
                .map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createStaff(UserDTO userDTO) {
        Role role = roleRepository.findById(STAFF_ROLE_ID)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Account account = accountRepository.findById(userDTO.getAccount_id())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        User user = UserMapper.mapToUser(userDTO, role, account);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO getStaffById(Long id) {
        User user = userRepository.findByIdAndRoleId(id, STAFF_ROLE_ID)
                .orElseThrow(() -> new UserNotFoundException("Staff not found"));
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public UserDTO updateStaff(Long id, UserDTO userDTO) {
        User user = userRepository.findByIdAndRoleId(id, STAFF_ROLE_ID)
                .orElseThrow(() -> new UserNotFoundException("Staff not found"));

        // Cập nhật thông tin user
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBirthday(userDTO.getBirthday());
        user.setAddress(userDTO.getAddress());
        user.setSex(userDTO.getSex());
        user.setAvatar(userDTO.getAvatar());
        user.setUpdatedAt(userDTO.getUpdatedAt());

        // Cập nhật isLock trong tài khoản
        if (userDTO.getAccount() != null) {
            Account account = user.getAccount();
            if (account != null) {
                account.setIsLock(userDTO.getAccount().getIsLock()); // <-- Dòng này rất quan trọng
            }
        }

        User updatedUser = userRepository.save(user);
        return UserMapper.mapToUserDTO(updatedUser);
    }


    @Override
    public void deleteStaff(Long id) {
        User user = userRepository.findByIdAndRoleId(id, STAFF_ROLE_ID)
                .orElseThrow(() -> new UserNotFoundException("Staff not found"));

        String avatarPath = user.getAvatar();
        if (avatarPath != null && !avatarPath.isEmpty()) {
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
