package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.CartItemDTO;
import com.javaweb.tour_booking.entity.Cart;
import com.javaweb.tour_booking.entity.CartItem;
import com.javaweb.tour_booking.entity.Tour;

public class CartItemMapper {
    public static CartItemDTO mapToCartItemDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setPrice(cartItem.getPrice());
        cartItemDTO.setCartId(cartItem.getCart().getId());
        cartItemDTO.setTourID(cartItem.getTour().getId());
        return cartItemDTO;
    }

    public static CartItem mapToCartItem(CartItemDTO cartItemDTO, Cart cart, Tour tour) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDTO.getId());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setPrice(cartItemDTO.getPrice());
        cartItem.setCart(cart);
        cartItem.setTour(tour);
        return cartItem;
    }
}
