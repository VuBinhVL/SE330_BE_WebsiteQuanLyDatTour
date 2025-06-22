package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.*;
import com.javaweb.tour_booking.dto.response.FavoriteTouristAttractionResponse;
import com.javaweb.tour_booking.dto.response.PopularTourRouteResponse;
import com.javaweb.tour_booking.dto.response.TourBookingDetailResponse;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.service.IBookingTransactionService;
import com.javaweb.tour_booking.service.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/tour")
@AllArgsConstructor
public class TourController {
    private final ITourService tourService;
    private final IBookingTransactionService bookingTransactionService;
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<TourDTO>>> getAllTours() {
        List<TourDTO> tours = tourService.GetAllTours();
        ApiResponse<List<TourDTO>> response = new ApiResponse<>("Lấy danh sách chuyến du lịch thành công", tours);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<TourDTO>> getTourById(@PathVariable Long id) {
        TourDTO tourDTO = tourService.GetTourById(id);
        ApiResponse<TourDTO> response = new ApiResponse<>("Đã tìm thấy chuyến du lịch", tourDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-list-tour-booking/{id}")
    public ResponseEntity<ApiResponse<List<TourBookingDetailResponse>>> getListTourBookingDetailByTourId(@PathVariable Long id) {
        List<TourBookingDetailResponse> tourBookingDetailResponse = tourService.getListTourBookingDetailByTourId(id);
        ApiResponse<List<TourBookingDetailResponse>> response = new ApiResponse<>("Danh sách phiếu đặt tour: ", tourBookingDetailResponse);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TourDTO>> createTour(@RequestBody TourDTO tourDTO) {
        TourDTO result = tourService.CreateTour(tourDTO);
        ApiResponse<TourDTO> response = new ApiResponse<>("Thêm tour thành công", result);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<TourDTO>> updateTour(@PathVariable Long id, @RequestBody TourDTO tourDTO) {
        TourDTO updated = tourService.UpdateTour(id, tourDTO);
        ApiResponse<TourDTO> response = new ApiResponse<>("Đã cập nhật thông tin chuyến du lịch thành công", updated);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTour(@PathVariable Long id) {
        tourService.DeleteTour(id);
        ApiResponse<String> response = new ApiResponse<>("Đã xóa chuyến du lịch thành công", null);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/top-5-popular-tour-routes")
    public ResponseEntity<ApiResponse<List<PopularTourRouteResponse>>> getTop5PopularTourRoutes() {
        List<PopularTourRouteResponse> attractions = tourService.getTop5PopularTourRoutes();
        ApiResponse<List<PopularTourRouteResponse>> response = new ApiResponse<>("Lấy top 5 tuyến du lịch được ưa thích thành công", attractions);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/create-booking-transaction")
    public ResponseEntity<ApiResponse<String>> createBookingTransaction(@RequestBody BookingTransactionRequest request) {
        try {
            bookingTransactionService.createBookingTransaction(
                    request.getInvoiceDTO(),
                    request.getTourBookingDTO(),
                    request.getTourBookingDetailDTO()
            );
            ApiResponse<String> response = new ApiResponse<>("Tạo giao dịch đặt tour thành công", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Lỗi: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Class DTO để nhận request
    public static class BookingTransactionRequest {
        private InvoiceDTO invoiceDTO;
        private TourBookingDTO tourBookingDTO;
        private TourBookingDetailDTO tourBookingDetailDTO;

        public InvoiceDTO getInvoiceDTO() { return invoiceDTO; }
        public void setInvoiceDTO(InvoiceDTO invoiceDTO) { this.invoiceDTO = invoiceDTO; }
        public TourBookingDTO getTourBookingDTO() { return tourBookingDTO; }
        public void setTourBookingDTO(TourBookingDTO tourBookingDTO) { this.tourBookingDTO = tourBookingDTO; }
        public TourBookingDetailDTO getTourBookingDetailDTO() { return tourBookingDetailDTO; }
        public void setTourBookingDetailDTO(TourBookingDetailDTO tourBookingDetailDTO) { this.tourBookingDetailDTO = tourBookingDetailDTO; }
    }

}
