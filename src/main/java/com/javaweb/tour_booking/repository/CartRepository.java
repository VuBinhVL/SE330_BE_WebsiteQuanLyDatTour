package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Account;
import com.javaweb.tour_booking.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}
