package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.CartDTO;
import com.javaweb.tour_booking.dto.CartItemDTO;
import com.javaweb.tour_booking.service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/cart")
@AllArgsConstructor
public class CartController {
    private final ICartService cartService;

    // Get or create a cart for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<CartDTO>> getCartByUserId(@PathVariable Long userId) {
        CartDTO cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(new ApiResponse<>("Fetched or created cart for user", cart));
    }

    // Get all items in a user's cart
    @GetMapping("/user/{userId}/items")
    public ResponseEntity<ApiResponse<List<CartItemDTO>>> getCartItems(@PathVariable Long userId) {
        CartDTO cart = cartService.getCartByUserId(userId);
        List<CartItemDTO> items = cartService.getCartItems(cart.getId());
        return ResponseEntity.ok(new ApiResponse<>("Fetched all items in user's cart", items));
    }

    // Add an item to a user's cart
    @PostMapping("/user/{userId}/items/add")
    public ResponseEntity<ApiResponse<CartItemDTO>> addCartItem(
            @PathVariable Long userId,
            @RequestBody CartItemDTO cartItemDTO) {
        CartDTO cart = cartService.getCartByUserId(userId);
        CartItemDTO added = cartService.addCartItem(cart.getId(), cartItemDTO);
        return ResponseEntity.ok(new ApiResponse<>("Added item to user's cart", added));
    }

    // Update quantity of a cart item
    @PutMapping("/items/{cartItemId}/update-quantity")
    public ResponseEntity<ApiResponse<CartItemDTO>> updateCartItemQuantity(
            @PathVariable Long cartItemId,
            @RequestParam int quantity) {
        CartItemDTO updated = cartService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok(new ApiResponse<>("Updated cart item quantity", updated));
    }

    // Remove a cart item
    @DeleteMapping("/items/{cartItemId}/remove")
    public ResponseEntity<ApiResponse<String>> removeCartItem(@PathVariable Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.ok(new ApiResponse<>("Removed cart item", null));
    }

    // Clear all items in a user's cart
    @DeleteMapping("/user/{userId}/clear")
    public ResponseEntity<ApiResponse<String>> clearCart(@PathVariable Long userId) {
        CartDTO cart = cartService.getCartByUserId(userId);
        cartService.clearCart(cart.getId());
        return ResponseEntity.ok(new ApiResponse<>("Cleared all items in user's cart", null));
    }
}