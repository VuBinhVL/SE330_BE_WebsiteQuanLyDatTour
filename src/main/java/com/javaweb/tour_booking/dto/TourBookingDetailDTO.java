package com.javaweb.tour_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourBookingDetailDTO {
    private Long id;
    private Boolean isContact;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userMemberId;
    private Long tourBookingId;
}
