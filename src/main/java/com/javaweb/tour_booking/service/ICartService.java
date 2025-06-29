package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.request.CartItemRequest;
import com.javaweb.tour_booking.entity.CartItem;

public interface ICartService {
    CartItem addToCart(CartItemRequest request);
}
