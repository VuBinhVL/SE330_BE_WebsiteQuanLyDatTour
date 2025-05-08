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
@Table(name = "tour_route_attraction")
public class TourRouteAttraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order", nullable = false)
    private int order;

    @Column(name = "action_description", nullable = false)
    private String actionDescription;

    // Thiết lập khóa ngoại đến bảng "tour_route"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ tour_route khi không cần
    @JoinColumn(name = "tour_route_id", nullable = false)
    private TourRoute tourRoute;

    // Thiết lập khóa ngoại đến bảng "tourist_attraction"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ tourist_attraction khi không cần
    @JoinColumn(name = "tourist_attraction_id", nullable = false)
    private TouristAttraction touristAttraction;
}
