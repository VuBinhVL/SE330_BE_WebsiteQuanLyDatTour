package com.javaweb.tour_booking.controller;

import com.javaweb.tour_booking.dto.UserDTO;
import com.javaweb.tour_booking.dto.request.LoginRequest;
import com.javaweb.tour_booking.dto.request.RegisterRequest;
import com.javaweb.tour_booking.dto.response.LoginResponse;
import com.javaweb.tour_booking.dto.response.RegisterResponse;
import com.javaweb.tour_booking.service.service_impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse Login(@RequestBody LoginRequest request) {
       return authService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> Register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        if (response.isSuccess())
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.badRequest().body(response);
    }
}
