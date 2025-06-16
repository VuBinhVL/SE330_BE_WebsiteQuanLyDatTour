package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.request.LoginRequest;
import com.javaweb.tour_booking.dto.response.LoginResponse;
import com.javaweb.tour_booking.entity.Account;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.repository.AccountRepository;
import com.javaweb.tour_booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

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

}
