package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.UserMemberDTO;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.entity.UserMember;
import com.javaweb.tour_booking.mapper.UserMemberMapper;
import com.javaweb.tour_booking.repository.UserMemberRepository;
import com.javaweb.tour_booking.repository.UserRepository;
import com.javaweb.tour_booking.service.IUserMemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserMemberServiceImpl implements IUserMemberService {
    private final UserMemberRepository userMemberRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserMemberDTO> getAllUserMembers() {
        return userMemberRepository.findAll()
                .stream()
                .map(UserMemberMapper::mapToUserMemberDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserMemberDTO getUserMemberById(Long id) {
        UserMember userMember = userMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserMember not found"));
        return UserMemberMapper.mapToUserMemberDTO(userMember);
    }

    @Override
    public UserMemberDTO createUserMember(UserMemberDTO userMemberDTO) {
        User user = userRepository.findById(userMemberDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserMember userMember = UserMemberMapper.mapToUserMember(userMemberDTO, user);
        UserMember saved = userMemberRepository.save(userMember);
        return UserMemberMapper.mapToUserMemberDTO(saved);
    }

    @Override
    public UserMemberDTO updateUserMember(Long id, UserMemberDTO userMemberDTO) {
        UserMember userMember = userMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserMember not found"));
        User user = userRepository.findById(userMemberDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMember.setFullname(userMemberDTO.getFullname());
        userMember.setEmail(userMemberDTO.getEmail());
        userMember.setPhoneNumber(userMemberDTO.getPhoneNumber());
        userMember.setBirthday(userMemberDTO.getBirthday());
        userMember.setSex(userMemberDTO.getSex());
        userMember.setCreatedAt(userMemberDTO.getCreatedAt());
        userMember.setUpdatedAt(userMemberDTO.getUpdatedAt());
        userMember.setUser(user);
        UserMember updated = userMemberRepository.save(userMember);
        return UserMemberMapper.mapToUserMemberDTO(updated);
    }

    @Override
    public void deleteUserMember(Long id) {
        if (!userMemberRepository.existsById(id)) {
            throw new RuntimeException("UserMember not found");
        }
        userMemberRepository.deleteById(id);
    }

    @Override
    public List<UserMemberDTO> getUserMembersByUserId(Long userId) {
        List<UserMember> members = userMemberRepository.findByUserId(userId);
        return members.stream()
                .map(UserMemberMapper::mapToUserMemberDTO)
                .collect(Collectors.toList());
    }
}