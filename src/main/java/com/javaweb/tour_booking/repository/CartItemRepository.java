package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
