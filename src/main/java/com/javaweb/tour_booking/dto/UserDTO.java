package com.javaweb.tour_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String fullname;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private String address;
    private Boolean sex;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long role_id;
    private Long account_id;
}
