package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.request.LoginRequest;
import com.javaweb.tour_booking.dto.request.RegisterRequest;
import com.javaweb.tour_booking.dto.response.LoginResponse;
import com.javaweb.tour_booking.dto.response.RegisterResponse;
import com.javaweb.tour_booking.entity.Account;
import com.javaweb.tour_booking.entity.Role;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.entity.UserMember;
import com.javaweb.tour_booking.repository.AccountRepository;
import com.javaweb.tour_booking.repository.RoleRepository;
import com.javaweb.tour_booking.repository.UserMemberRepository;
import com.javaweb.tour_booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMemberRepository userMemberRepository;

    public LoginResponse login(LoginRequest request) {
        try {
            Account account = accountRepository.findByUsername(request.getUsername()).orElse(null);
            if (account == null) {
                return new LoginResponse(false, "Tên đăng nhập hoặc mật khẩu sai", null, null);
            }

            if (!account.getPassword().equals(request.getPassword())) {
                return new LoginResponse(false, "Tên đăng nhập hoặc mật khẩu sai", null, null);
            }

            if (account.getIsLock()) {
                return new LoginResponse(false, "Tài khoản đang bị khóa", null, null);
            }

            User user = userRepository.findByAccount(account).orElse(null);
            if (user == null) {
                return new LoginResponse(false, "Không tìm thấy người dùng", null, null);
            }

            return new LoginResponse(
                    true,
                    "Đăng nhập thành công",
                    user.getRole().getName(),
                    user.getId()
            );
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console
            return new LoginResponse(false, "Lỗi hệ thống: " + e.getMessage(), null, null);
        }
    }

    public RegisterResponse register(RegisterRequest request) {
        //Kiểm tra username tồn tại chưa
        if (accountRepository.findByUsername(request.getUsername()).isPresent()) {
            return new RegisterResponse(false, "Tên đăng nhập đã tồn tại");
        }

        //Kiểm tra email tồn tại chưa
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return new RegisterResponse(false, "Email đã tồn tại");
        }

        //Tạo tài khoản
        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(request.getPassword());
        account.setIsLock(false);
        account = accountRepository.save(account);

        //Tìm Role Customer
        Role roleAccout = roleRepository.findById(2L).orElseThrow(() -> new RuntimeException("Không tìm thấy quyền khách hàng"));

        //Tạo người dùng
        User user = new User();
        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());
        user.setAddress("Chưa có");
        user.setPhoneNumber(request.getPhoneNumber());
        user.setBirthday(request.getBirthday());
        user.setAvatar("default.png");
        user.setSex(request.getSex());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setAccount(account);
        user.setRole(roleAccout);
        userRepository.save(user);

        //Thêm người dùng vào user member
        UserMember  userMember = new UserMember();
        userMember.setFullname(request.getFullname());
        userMember.setEmail(request.getEmail());
        userMember.setPhoneNumber(request.getPhoneNumber());
        userMember.setBirthday(request.getBirthday());
        userMember.setSex(request.getSex());
        userMember.setCreatedAt(LocalDateTime.now());
        userMember.setUpdatedAt(LocalDateTime.now());
        userMember.setUser(user);
        userMemberRepository.save(userMember);
        return new RegisterResponse(true, "Đăng ký tài khoản thành công");
    }
}
