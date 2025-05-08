package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.CartDTO;
import com.javaweb.tour_booking.entity.Cart;
import com.javaweb.tour_booking.entity.User;

public class CartMapper {
    public static CartDTO mapToCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setCreatedAt(cart.getCreatedAt());
        cartDTO.setUpdatedAt(cart.getUpdatedAt());
        cartDTO.setId(cart.getId());
        return cartDTO;
    }

    public static Cart mapToCart(CartDTO cartDTO, User user) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setCreatedAt(cartDTO.getCreatedAt());
        cart.setUpdatedAt(cartDTO.getUpdatedAt());
        cart.setUser(user);
        return cart;
    }
}
