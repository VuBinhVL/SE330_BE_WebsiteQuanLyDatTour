package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.UserDTO;
import com.javaweb.tour_booking.entity.Account;
import com.javaweb.tour_booking.entity.Role;
import com.javaweb.tour_booking.entity.User;

public class UserMapper {
    public static UserDTO mapToUserDTO (User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullname(user.getFullname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setAddress(user.getAddress());
        userDTO.setSex(user.getSex());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        userDTO.setRole_id(user.getRole().getId());
        userDTO.setAccount_id(user.getAccount().getId());
        if (user.getAccount() != null) {
            userDTO.setAccount_id(user.getAccount().getId());
            userDTO.setAccount(AccountMapper.mapToAccountDTO(user.getAccount()));
        }
        return userDTO;
    }

    public static User mapToUser (UserDTO userDTO, Role role, Account account) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setBirthday(userDTO.getBirthday());
        user.setAddress(userDTO.getAddress());
        user.setSex(userDTO.getSex());
        user.setAvatar(userDTO.getAvatar());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        user.setRole(role);
        user.setAccount(account);
        return user;
    }
}
