package com.javaweb.tour_booking.exception;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    // Xử lý lỗi TouristAttractionNotFound
    @ExceptionHandler(TouristAttractionNotFound.class)
    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(TouristAttractionNotFound ex) {
        ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    //Xử lý lỗi
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        ApiResponse<Object> response = new ApiResponse<>("Lỗi hệ thống: " + ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
