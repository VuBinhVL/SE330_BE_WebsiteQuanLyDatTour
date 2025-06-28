package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);
    void deleteAllByIdInBatch(Iterable<Long> ids);
}
