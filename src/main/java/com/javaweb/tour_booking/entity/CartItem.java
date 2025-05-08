package com.javaweb.tour_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    // Thiết lập khóa ngoại đến bảng "Tour"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ Tour khi không cần
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    // Thiết lập khóa ngoại đến bảng "Cart"
    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh load toàn bộ Cart khi không cần
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
}
