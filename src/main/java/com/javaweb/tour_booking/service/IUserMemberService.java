package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.UserMemberDTO;
import java.util.List;

public interface IUserMemberService {
    List<UserMemberDTO> getAllUserMembers();
    UserMemberDTO getUserMemberById(Long id);
    UserMemberDTO createUserMember(UserMemberDTO userMemberDTO);
    UserMemberDTO updateUserMember(Long id, UserMemberDTO userMemberDTO);
    void deleteUserMember(Long id);
    List<UserMemberDTO> getUserMembersByUserId(Long userId);
}