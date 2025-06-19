package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.AccountDTO;
import com.javaweb.tour_booking.entity.Account;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.mapper.AccountMapper;
import com.javaweb.tour_booking.repository.AccountRepository;
import com.javaweb.tour_booking.repository.UserRepository;
import com.javaweb.tour_booking.service.IAccountService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.FileSystemResource;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public void changePasswordAndSendEmail(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new RuntimeException("User not found for this account"));

        String newPassword = generateRandomPassword(8);
        account.setPassword(newPassword);
        accountRepository.save(account);

        sendNewPasswordEmail(user.getEmail(), newPassword);
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(idx));
        }
        return sb.toString();
    }

    private void sendNewPasswordEmail(String toEmail, String newPassword) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("Your New Password");
            helper.setText(
                    "<h2>Password Reset</h2>" +
                            "<p>Your new password is: <b>" + newPassword + "</b></p>" +
                            "<p>Please change it after logging in.</p>", true);

            // Example: attach an image (logo.png) from resources


            // Example: attach a PDF (guide.pdf) from resources
            // helper.addAttachment("guide.pdf", new ClassPathResource("static/guide.pdf"));

            // Set headers to reduce spam
            message.setHeader("X-Priority", "1");
            message.setHeader("X-Mailer", "Spring Boot Mail Sender");
            message.setHeader("Return-Path", "your_email@gmail.com");

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public boolean verifyPassword(Long accountId, String password) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getPassword().equals(password);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(AccountMapper::mapToAccountDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setUsername(accountDTO.getUsername());
        account.setPassword(accountDTO.getPassword());
        account.setIsLock(accountDTO.getIsLock());
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found");
        }
        accountRepository.deleteById(id);
    }
}