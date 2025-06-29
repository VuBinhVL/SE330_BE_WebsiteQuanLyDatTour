package com.javaweb.tour_booking.controller.customer;

import com.javaweb.tour_booking.dto.request.InvoiceRequest;
import com.javaweb.tour_booking.dto.response.InvoiceHistoryResponse;
import com.javaweb.tour_booking.service.IInvoiceService;
import com.javaweb.tour_booking.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceCustomerController {
    private final IInvoiceService invoiceService;
    private final IPaymentService paymentService;


    @GetMapping("/{userId}")
    public ResponseEntity<List<InvoiceHistoryResponse>> getHistory(@PathVariable Long userId) {
        return new ResponseEntity<>(invoiceService.getHistoryInvoiceByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/payment-cash")
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceRequest request) {
        try {
            paymentService.createInvoiceWithBookings(request);
            return ResponseEntity.ok(Map.of("message", "Đặt hàng thành công"));
        }
        catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
