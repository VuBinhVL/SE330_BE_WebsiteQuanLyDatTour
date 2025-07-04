package com.javaweb.tour_booking.controller.customer;

import com.javaweb.tour_booking.dto.request.CartItemRequest;
import com.javaweb.tour_booking.dto.response.CartItemResponse;
import com.javaweb.tour_booking.entity.CartItem;
import com.javaweb.tour_booking.service.ICartItemService;
import com.javaweb.tour_booking.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartCustomerController {

    private final ICartItemService cartItemService;
    private final ICartService cartService;


    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemResponse>> getCartByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(cartItemService.getCartItemsByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping("/items")
    public ResponseEntity<?> deleteCartItems(@RequestBody List<Long> itemIds) {
        try {
            cartItemService.deleteCartItems(itemIds);
            return ResponseEntity.ok(Map.of("message", "Xóa thành công"));
        }
        catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest request) {
        try {
            CartItem item = cartService.addToCart(request);
            return ResponseEntity.ok(Map.of("message", "Đã thêm vào giỏ hàng"));
        }
        catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
