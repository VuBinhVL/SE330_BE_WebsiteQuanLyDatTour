package com.javaweb.tour_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "galley")
public class Galley {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "thumb_nail", nullable = false)
    private String thumbNail;

    // Thiết lập khóa ngoại đến bảng "tourist_attraction"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ tourist_attraction khi không cần
    @JoinColumn(name = "tourist_attraction_id", nullable = false)
    private TouristAttraction touristAttraction;
}
