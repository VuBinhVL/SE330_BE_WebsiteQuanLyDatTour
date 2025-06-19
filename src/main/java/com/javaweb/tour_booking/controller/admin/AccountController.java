package com.javaweb.tour_booking.controller.admin;

import com.javaweb.tour_booking.common.ApiResponse;
import com.javaweb.tour_booking.dto.AccountDTO;
import com.javaweb.tour_booking.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/account")
@AllArgsConstructor
public class AccountController {
    private final IAccountService accountService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<AccountDTO>>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        ApiResponse<List<AccountDTO>> response = new ApiResponse<>("Fetched account list successfully", accounts);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> getAccountById(@PathVariable Long id) {
        AccountDTO account = accountService.getAccountById(id);
        ApiResponse<AccountDTO> response = new ApiResponse<>("Account found", account);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AccountDTO>> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO created = accountService.createAccount(accountDTO);
        ApiResponse<AccountDTO> response = new ApiResponse<>("Account created successfully", created);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        AccountDTO updated = accountService.updateAccount(id, accountDTO);
        ApiResponse<AccountDTO> response = new ApiResponse<>("Account updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        ApiResponse<String> response = new ApiResponse<>("Account deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}