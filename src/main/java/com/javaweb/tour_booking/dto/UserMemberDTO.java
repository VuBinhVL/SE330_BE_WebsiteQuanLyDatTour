package com.javaweb.tour_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserMemberDTO {
    private Long id;
    private String fullname;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private Boolean sex;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
}
