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
@Table(name = "favorite_tour")
public class FavoriteTour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Thiết lập khóa ngoại đến bảng "user"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ user khi không cần
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Thiết lập khóa ngoại đến bảng "tour_route"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ tour_route khi không cần
    @JoinColumn(name = "tour_route_id", nullable = false)
    private TourRoute tourRoute;
}
