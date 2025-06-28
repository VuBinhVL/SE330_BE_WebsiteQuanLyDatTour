package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.response.CartItemResponse;

import java.util.List;

public interface ICartItemService {
    public List<CartItemResponse> getCartItemsByUserId(Long userId);
    public void deleteCartItems(List<Long> ids);
}
