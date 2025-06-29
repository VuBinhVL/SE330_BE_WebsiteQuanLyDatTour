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
import com.javaweb.tour_booking.dto.CartDTO;
import com.javaweb.tour_booking.dto.CartItemDTO;
import com.javaweb.tour_booking.entity.Cart;
import com.javaweb.tour_booking.entity.CartItem;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.mapper.CartMapper;
import com.javaweb.tour_booking.mapper.CartItemMapper;
import com.javaweb.tour_booking.repository.CartRepository;
import com.javaweb.tour_booking.repository.CartItemRepository;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.repository.UserRepository;
import com.javaweb.tour_booking.service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
        CartDTO cartDTO = CartMapper.mapToCartDTO(cart);
        cartDTO.setUserId(userId);
        return cartDTO;
    }

    @Override
    public List<CartItemDTO> getCartItems(Long cartId) {
        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        return items.stream()
                .map(CartItemMapper::mapToCartItemDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemDTO addCartItem(Long cartId, CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        Tour tour = tourRepository.findById(cartItemDTO.getTourID())
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        CartItem cartItem = CartItemMapper.mapToCartItem(cartItemDTO, cart, tour);
        CartItem saved = cartItemRepository.save(cartItem);
        return CartItemMapper.mapToCartItemDTO(saved);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new RuntimeException("Cart item not found");
        }
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public CartItemDTO updateCartItemQuantity(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(quantity);
        CartItem updated = cartItemRepository.save(cartItem);
        return CartItemMapper.mapToCartItemDTO(updated);
    }

    @Override
    public void clearCart(Long cartId) {
        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        List<Long> ids = items.stream().map(CartItem::getId).collect(Collectors.toList());
        cartItemRepository.deleteAllByIdInBatch(ids);
    }
}