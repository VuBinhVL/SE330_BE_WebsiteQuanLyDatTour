package com.javaweb.tour_booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "sex", nullable = false)
    private Boolean sex;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Thiết lập khóa ngoại đến bảng "role"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ Role khi không cần
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // Thiết lập khóa ngoại đến bảng "account"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ account khi không cần
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
