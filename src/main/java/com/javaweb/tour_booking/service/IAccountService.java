package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.AccountDTO;
import java.util.List;

public interface IAccountService {
    List<AccountDTO> getAllAccounts();
    AccountDTO getAccountById(Long id);
    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO updateAccount(Long id, AccountDTO accountDTO);
    void deleteAccount(Long id);
    boolean verifyPassword(Long accountId, String password);
    // New method for changing password and sending email
    void changePasswordAndSendEmail(Long accountId);
    void sendAccountCredentialsEmail(Long accountId);
}