package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.CartDTO;
import com.javaweb.tour_booking.dto.CartItemDTO;

import java.util.List;

public interface ICartService {
    CartDTO getCartByUserId(Long userId);
    List<CartItemDTO> getCartItems(Long cartId);
    CartItemDTO addCartItem(Long cartId, CartItemDTO cartItemDTO);
    void removeCartItem(Long cartItemId);
    CartItemDTO updateCartItemQuantity(Long cartItemId, int quantity);
    void clearCart(Long cartId);
}