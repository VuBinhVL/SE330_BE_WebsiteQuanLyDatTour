package com.javaweb.tour_booking.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String fullname;
    private Boolean sex;
    private LocalDate birthday;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;

}
