package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.response.CartItemResponse;
import com.javaweb.tour_booking.entity.Cart;
import com.javaweb.tour_booking.entity.CartItem;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.repository.CartItemRepository;
import com.javaweb.tour_booking.repository.CartRepository;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.repository.TourRouteRepository;
import com.javaweb.tour_booking.service.ICartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements ICartItemService {
    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    private TourRepository tourRepository;

    //Lấy danh sách các item trong giỏ hàng
    @Override
    public List<CartItemResponse> getCartItemsByUserId(Long userId) {
        //1. Tìm giỏ hàng
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giò hàng"));

        //2. Lấy danh sách item giỏ hàng của người dùng
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());

        return cartItems.stream().map(cartItem -> {
            Tour tour = cartItem.getTour();
            TourRoute route = tour.getTourRoute();

            Duration duration = Duration.between(tour.getDepatureDate(), tour.getReturnDate());
            long days = duration.toDays();

            return new CartItemResponse(
                    cartItem.getId(),
                    route.getId(),
                    route.getRouteName(),
                    route.getImage(),
                    route.getStartLocation(),
                    route.getEndLocation(),
                    tour.getId(),
                    days + " ngày",
                    cartItem.getDepartureDay().toLocalDate(),
                    tour.getPrice(),
                    cartItem.getQuantity(),
                    tour.getTotalSeats() - tour.getBookedSeats()
            );
        }).collect(Collectors.toList());
    }

    //Xóa các item trong giỏ hàng
    @Override
    public void deleteCartItems(List<Long> ids) {
        for (Long id : ids) {
            cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy item"));
        }
        cartItemRepository.deleteAllByIdInBatch(ids);
    }
}
