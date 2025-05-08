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
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "depature_date", nullable = false)
    private LocalDateTime depatureDate;

    @Column(name = "return_date", nullable = false)
    private LocalDateTime returnDate;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "total_seats", nullable = false)
    private int totalSeats;

    @Column(name = "booked_seats", nullable = false)
    private int bookedSeats;

    @Column(name = "pick_up_time", nullable = false)
    private LocalDateTime pickUpTime;

    @Column(name = "pick_up_location", nullable = false)
    private String pickUpLocation;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Thiết lập khóa ngoại đến bảng "Tour_Route"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ Tour_Route khi không cần
    @JoinColumn(name = "tour_route_id", nullable = false)
    private TourRoute tourRoute;
}
