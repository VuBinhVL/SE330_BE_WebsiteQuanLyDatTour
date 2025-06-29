package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.request.CartItemRequest;
import com.javaweb.tour_booking.entity.Cart;
import com.javaweb.tour_booking.entity.CartItem;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.repository.CartItemRepository;
import com.javaweb.tour_booking.repository.CartRepository;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.repository.UserRepository;
import com.javaweb.tour_booking.service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements ICartService {
    private CartRepository cartRepository;

    private TourRepository tourRepository;

    private CartItemRepository cartItemRepository;

    private UserRepository userRepository;

    //Thêm item vào cart của user
    @Override
    public CartItem addToCart(CartItemRequest request) {
        Cart cart = cartRepository.findByUserId(request.getUserId())
                .orElseGet(() -> {
                    User user = userRepository.findById(request.getUserId())
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Tour tour = tourRepository.findById(request.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour không tồn tại"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setTour(tour);
        cartItem.setQuantity(request.getQuantity());
        cartItem.setPrice(tour.getPrice()); // hoặc tính lại nếu cần
        cartItem.setDepartureDay(request.getDepartureDay());

        return cartItemRepository.save(cartItem);
    }
}
