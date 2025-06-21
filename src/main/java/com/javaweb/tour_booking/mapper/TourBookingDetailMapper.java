package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.TourBookingDetailDTO;
import com.javaweb.tour_booking.entity.TourBooking;
import com.javaweb.tour_booking.entity.TourBookingDetail;
import com.javaweb.tour_booking.entity.UserMember;

import java.time.LocalDateTime;

public class TourBookingDetailMapper {
    public static TourBookingDetailDTO mapToTourBookingDetailDTO(TourBookingDetail detail) {
        TourBookingDetailDTO dto = new TourBookingDetailDTO();
        dto.setId(detail.getId());
        dto.setIsContact(detail.getIsContact());
        dto.setCreatedAt(detail.getCreatedAt());
        dto.setUpdatedAt(detail.getUpdatedAt());
        dto.setUserMemberId(detail.getUserMember().getId());
        dto.setTourBookingId(detail.getTourBooking().getId());
        return dto;
    }


        public static TourBookingDetail mapToTourBookingDetail(TourBookingDetailDTO dto, UserMember userMember, TourBooking tourBooking, boolean isCreate) {
            TourBookingDetail detail = new TourBookingDetail();
            detail.setId(dto.getId());
            detail.setIsContact(dto.getIsContact());
            detail.setUserMember(userMember);
            detail.setTourBooking(tourBooking);

            // Logic cho createdAt và updatedAt
            if (isCreate) {
                // Khi tạo mới: createdAt = thời gian hiện tại, updatedAt = null
                detail.setCreatedAt(LocalDateTime.now());
                detail.setUpdatedAt(null);
            } else {
                // Khi sửa: giữ nguyên createdAt, cập nhật updatedAt
                detail.setCreatedAt(dto.getCreatedAt());
                detail.setUpdatedAt(LocalDateTime.now());
            }

            return detail;
        }
    }

