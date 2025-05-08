package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
