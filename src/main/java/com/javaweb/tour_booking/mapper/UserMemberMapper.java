package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.UserMemberDTO;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.entity.UserMember;

public class UserMemberMapper {
    public static UserMemberDTO mapToUserMemberDTO(UserMember userMember) {
        UserMemberDTO dto = new UserMemberDTO();
        dto.setId(userMember.getId());
        dto.setFullname(userMember.getFullname());
        dto.setEmail(userMember.getEmail());
        dto.setPhoneNumber(userMember.getPhoneNumber());
        dto.setBirthday(userMember.getBirthday());
        dto.setSex(userMember.getSex());
        dto.setCreatedAt(userMember.getCreatedAt());
        dto.setUpdatedAt(userMember.getUpdatedAt());
        dto.setUserId(userMember.getUser().getId());
        return dto;
    }

    public static UserMember mapToUserMember(UserMemberDTO dto, User user) {
        UserMember userMember = new UserMember();
        userMember.setId(dto.getId());
        userMember.setFullname(dto.getFullname());
        userMember.setEmail(dto.getEmail());
        userMember.setPhoneNumber(dto.getPhoneNumber());
        userMember.setBirthday(dto.getBirthday());
        userMember.setSex(dto.getSex());
        userMember.setCreatedAt(dto.getCreatedAt());
        userMember.setUpdatedAt(dto.getUpdatedAt());
        userMember.setUser(user);
        return userMember;
    }
}
