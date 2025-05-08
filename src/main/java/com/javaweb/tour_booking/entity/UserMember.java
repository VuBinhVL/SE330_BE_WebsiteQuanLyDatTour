package com.javaweb.tour_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_member")
public class UserMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "sex", nullable = false)
    private Boolean sex;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Thiết lập khóa ngoại đến bảng "user"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ user khi không cần
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
