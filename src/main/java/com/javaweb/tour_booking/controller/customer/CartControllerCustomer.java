package com.javaweb.tour_booking.controller.customer;

import com.javaweb.tour_booking.dto.response.CartItemResponse;
import com.javaweb.tour_booking.repository.CartItemRepository;
import com.javaweb.tour_booking.service.ICartItemService;
import com.javaweb.tour_booking.service.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartControllerCustomer {

    private final ICartItemService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemResponse>> getCartByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.getCartItemsByUserId(userId), HttpStatus.OK);
    }
}
