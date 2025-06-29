package com.javaweb.tour_booking.controller.customer;

import com.javaweb.tour_booking.dto.response.InvoiceHistoryResponse;
import com.javaweb.tour_booking.service.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceCustomerController {
    private final IInvoiceService invoiceService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<InvoiceHistoryResponse>> getHistory(@PathVariable Long userId) {
        return new ResponseEntity<>(invoiceService.getHistoryInvoiceByUserId(userId), HttpStatus.OK);
    }


}
