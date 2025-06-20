package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.FavoriteTourDTO;
import com.javaweb.tour_booking.service.IFavoriteTourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.javaweb.tour_booking.dto.response.FavTourResponse;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/favorite-tour")
@AllArgsConstructor
public class FavouriteTourController {
    private final IFavoriteTourService favoriteTourService;

    // Lấy tất cả tour yêu thích
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<FavoriteTourDTO>>> getAllFavoriteTours() {
        List<FavoriteTourDTO> favorites = favoriteTourService.getAllFavoriteTours();
        ApiResponse<List<FavoriteTourDTO>> response = new ApiResponse<>("Lấy danh sách tour yêu thích thành công", favorites);
        return ResponseEntity.ok(response);
    }

    // Lấy tour yêu thích theo ID
    @PostMapping("/get/{id}")
    public ResponseEntity<ApiResponse<FavoriteTourDTO>> getFavoriteTourById(@PathVariable Long id) {
        FavoriteTourDTO favorite = favoriteTourService.getFavoriteTourById(id);
        ApiResponse<FavoriteTourDTO> response = new ApiResponse<>("Đã tìm thấy tour yêu thích", favorite);
        return ResponseEntity.ok(response);
    }

    // Thêm tour yêu thích
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<FavoriteTourDTO>> addFavoriteTour(@RequestBody FavoriteTourDTO favoriteTourDTO) {
        FavoriteTourDTO created = favoriteTourService.addFavoriteTour(favoriteTourDTO);
        ApiResponse<FavoriteTourDTO> response = new ApiResponse<>("Thêm tour yêu thích thành công", created);
        return ResponseEntity.ok(response);
    }

    // Xóa tour yêu thích
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ApiResponse<String>> removeFavoriteTour(@PathVariable Long id) {
        favoriteTourService.removeFavoriteTour(id);
        ApiResponse<String> response = new ApiResponse<>("Xóa tour yêu thích thành công", null);
        return ResponseEntity.ok(response);
    }

    // Lấy danh sách tour yêu thích theo user
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<FavoriteTourDTO>>> getFavoriteToursByUserId(@PathVariable Long userId) {
        List<FavoriteTourDTO> favorites = favoriteTourService.getFavoriteToursByUserId(userId);
        ApiResponse<List<FavoriteTourDTO>> response = new ApiResponse<>("Lấy danh sách tour yêu thích của người dùng thành công", favorites);
        return ResponseEntity.ok(response);
    }

    // Lấy danh sách tour yêu thích chi tiết theo user
    @GetMapping("/user/{userId}/response")
    public ResponseEntity<ApiResponse<List<FavTourResponse>>> getFavoriteTourResponsesByUserId(@PathVariable Long userId) {
        List<FavTourResponse> favorites = favoriteTourService.getFavoriteToursResponseByUserId(userId);
        ApiResponse<List<FavTourResponse>> response = new ApiResponse<>("Lấy danh sách tour yêu thích (chi tiết) của người dùng thành công", favorites);
        return ResponseEntity.ok(response);
    }
}