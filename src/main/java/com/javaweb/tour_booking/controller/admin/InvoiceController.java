package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.InvoiceDTO;
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

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<InvoiceDTO>>> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.GetAllInvoices();
        ApiResponse<List<InvoiceDTO>> response = new ApiResponse<>("Invoices fetched successfully", invoices);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<InvoiceDTO>> getInvoiceById(@PathVariable Long id) {
        InvoiceDTO invoice = invoiceService.GetInvoiceById(id);
        ApiResponse<InvoiceDTO> response = new ApiResponse<>("Invoice fetched successfully", invoice);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<InvoiceDTO>> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO dto) {
        InvoiceDTO updated = invoiceService.UpdateInvoice(id, dto);
        ApiResponse<InvoiceDTO> response = new ApiResponse<>("Invoice updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteInvoice(@PathVariable Long id) {
        invoiceService.DeleteInvoice(id);
        ApiResponse<String> response = new ApiResponse<>("Invoice deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
