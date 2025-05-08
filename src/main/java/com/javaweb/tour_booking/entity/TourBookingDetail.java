package com.javaweb.tour_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tour_booking_detail")
public class TourBookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_contact", nullable = false)
    private Boolean isContact;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Thiết lập khóa ngoại đến bảng "user_member"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ userMember khi không cần
    @JoinColumn(name = "user_member_id", nullable = false)
    private UserMember userMember;

    // Thiết lập khóa ngoại đến bảng "tour_booking"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ tour_booking khi không cần
    @JoinColumn(name = "tour_booking_id", nullable = false)
    private TourBooking tourBooking;
}
