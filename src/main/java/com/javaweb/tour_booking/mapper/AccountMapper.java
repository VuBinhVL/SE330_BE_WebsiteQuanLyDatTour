package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.AccountDTO;
import com.javaweb.tour_booking.entity.Account;

public class AccountMapper {
    public static AccountDTO mapToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setIsLock(account.getIsLock());
        return accountDTO;
    }
    public static Account mapToAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setUsername(accountDTO.getUsername());
        account.setPassword(accountDTO.getPassword());
        account.setIsLock(accountDTO.getIsLock());
        return account;
    }
}
