package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.InvoiceDTO;
import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.service.IInvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/invoice")
@AllArgsConstructor
public class InvoiceController {
    private final IInvoiceService invoiceService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<InvoiceDTO>> createInvoice(@RequestBody InvoiceDTO dto) {
        InvoiceDTO created = invoiceService.CreateInvoice(dto);
        ApiResponse<InvoiceDTO> response = new ApiResponse<>("Invoice created successfully", created);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<InvoiceDTO>>> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.GetAllInvoices();
        ApiResponse<List<InvoiceDTO>> response = new ApiResponse<>("Invoices fetched successfully", invoices);
        return ResponseEntity.ok(response);
    }
}
