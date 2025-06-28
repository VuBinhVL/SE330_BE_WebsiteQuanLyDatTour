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
import java.io.UnsupportedEncodingException;
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
    public void sendAccountCredentialsEmail(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new RuntimeException("User not found for this account"));

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // ⚠️ Bắt UnsupportedEncodingException
            helper.setFrom("dangphuthien2005@gmail.com", "Tour Booking System");

            helper.setTo(user.getEmail());
            helper.setSubject("Thông tin tài khoản - Tour Booking");

            String content = """
            <div style="font-family: Arial, sans-serif; padding: 20px; color: #333;">
                <h2 style="color: #007bff;">[Tour Booking] Tài khoản của bạn</h2>
                <p>Xin chào <b>%s</b>,</p>
                <p>Thông tin tài khoản của bạn như sau:</p>
                <ul>
                    <li><b>Tên đăng nhập:</b> %s</li>
                    <li><b>Mật khẩu:</b> %s</li>
                </ul>
                <p>Vui lòng đổi mật khẩu sau khi đăng nhập để đảm bảo an toàn.</p>
                <hr style="margin-top: 30px;"/>
                <p style="font-size: 12px; color: gray;">
                    Đây là email tự động từ hệ thống Tour Booking. Vui lòng không phản hồi.
                </p>
                <p style="font-size: 12px; color: gray;">
                    📍 Trường Đại học ABC — Khoa CNTT<br/>
                    📧 Hỗ trợ: support@tourbooking.fake
                </p>
            </div>
        """.formatted(user.getFullname(), account.getUsername(), account.getPassword());

            helper.setText(content, true);

            message.setHeader("X-Priority", "1");
            message.setHeader("X-Mailer", "TourBookingMailer");

            javaMailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to send account credentials email", e);
        }
    }



    @Override
    public void changePasswordAndSendEmail(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new RuntimeException("User not found for this account"));

        String newPassword = generateRandomPassword(8);
        account.setPassword(newPassword);
        accountRepository.save(account);

        sendNewPasswordEmail(user.getFullname(), user.getEmail(), newPassword);
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

    private void sendNewPasswordEmail(String fullName, String toEmail, String newPassword) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("dangphuthien2005@gmail.com", "Tour Booking System");
            helper.setTo(toEmail);
            helper.setSubject("Mật khẩu mới - Tour Booking");

            String content = """
            <div style="font-family: Arial, sans-serif; padding: 20px; color: #333;">
                <h2 style="color: #dc3545;">[Tour Booking] Mật khẩu mới của bạn</h2>
                <p>Xin chào <b>%s</b>,</p>
                <p>Mật khẩu mới của bạn là: <b>%s</b></p>
                <p>Vui lòng đăng nhập và thay đổi mật khẩu ngay để bảo mật tài khoản.</p>
                <hr style="margin-top: 30px;"/>
                <p style="font-size: 12px; color: gray;">
                    Đây là email tự động từ hệ thống Tour Booking. Vui lòng không phản hồi.
                </p>
                <p style="font-size: 12px; color: gray;">
                    📍 Trường Đại học UIT — Khoa KTPM<br/>
                    📧 Hỗ trợ: support@tourbooking.fake
                </p>
            </div>
        """.formatted(fullName, newPassword);

            helper.setText(content, true);
            message.setHeader("X-Priority", "1");
            message.setHeader("X-Mailer", "TourBookingMailer");

            javaMailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
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
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        // Remove or update the user referencing this account
        userRepository.findByAccount(account).ifPresent(user -> {
            userRepository.deleteById(user.getId());
        });
        accountRepository.deleteById(id);
    }
}