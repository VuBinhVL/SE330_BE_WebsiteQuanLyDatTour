package com.javaweb.tour_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tour_booking")
public class TourBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seats_booked", nullable = false)
    private int seatsBooked;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Thiết lập khóa ngoại đến bảng "Tour"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ Tour khi không cần
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    // Thiết lập khóa ngoại đến bảng "user"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ user khi không cần
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Thiết lập khóa ngoại đến bảng "invoice"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ invoice khi không cần
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;
}
