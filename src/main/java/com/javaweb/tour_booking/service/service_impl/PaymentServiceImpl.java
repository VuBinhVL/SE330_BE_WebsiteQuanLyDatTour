package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.request.InvoiceRequest;
import com.javaweb.tour_booking.dto.request.PassengerDto;
import com.javaweb.tour_booking.dto.request.TourOrderDto;
import com.javaweb.tour_booking.entity.*;
import com.javaweb.tour_booking.repository.*;
import com.javaweb.tour_booking.service.IEmailService;
import com.javaweb.tour_booking.service.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {
    private InvoiceRepository invoiceRepo;
    private TourBookingRepository bookingRepo;
    private TourBookingDetailRepository detailRepo;
    private UserMemberRepository userMemberRepo;
    private UserRepository userRepo;
    private TourRepository tourRepo;
    private IEmailService emailService;

    //Thanh toán bằng tiền mặt
    @Override
    @Transactional
    public void createInvoiceWithBookings(InvoiceRequest request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));

        Invoice invoice = new Invoice();
        invoice.setPaymentMethod("Tiền mặt");
        invoice.setPaymentStatus(false); // CHỜ THANH TOÁN
        invoice.setCreatedAt( LocalDateTime.now());
        invoice.setTotalAmount(BigDecimal.ZERO);
        invoice = invoiceRepo.save(invoice);

        BigDecimal total = BigDecimal.ZERO;

        for (TourOrderDto order : request.getTours()) {
            Tour tour = tourRepo.findById(order.getTourId())
                    .orElseThrow(() -> new IllegalArgumentException("Tour không tồn tại"));

            if (tour.getBookedSeats() + order.getQuantity() > tour.getTotalSeats()) {
                throw new IllegalArgumentException("Không đủ chỗ cho tour " + tour.getId());
            }

            TourBooking booking = new TourBooking();
            booking.setInvoice(invoice);
            booking.setUser(user);
            booking.setTour(tour);
            booking.setSeatsBooked(order.getQuantity());
            BigDecimal bookingTotal = tour.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
            booking.setTotalPrice(bookingTotal);
            booking = bookingRepo.save(booking);

            for (int i = 0; i < order.getPassengers().size(); i++) {
                PassengerDto p = order.getPassengers().get(i);
                UserMember member = userMemberRepo
                        .findByEmailAndPhoneNumber(p.getEmail(), p.getPhoneNumber())
                        .orElse(null);

                if (member == null) {
                    boolean existsEmail = userMemberRepo.existsByEmail(p.getEmail());
                    boolean existsPhone = userMemberRepo.existsByPhoneNumber(p.getPhoneNumber());
                    if (existsEmail || existsPhone) {
                        throw new IllegalArgumentException("Hàng khách có số điện thoại hoặc email bị trùng");
                    }
                    // tạo mới
                    member = new UserMember();
                    member.setFullname(p.getFullname());
                    member.setEmail(p.getEmail());
                    member.setPhoneNumber(p.getPhoneNumber());
                    member.setUser(user);
                    member = userMemberRepo.save(member);
                }

                TourBookingDetail detail = new TourBookingDetail();
                detail.setTourBooking(booking);
                detail.setUserMember(member);
                detail.setIsContact(i == order.getContactIndex());
                detailRepo.save(detail);
            }

            tour.setBookedSeats(tour.getBookedSeats() + order.getQuantity());
            tourRepo.save(tour);
            total = total.add((tour.getPrice()).multiply(BigDecimal.valueOf(order.getQuantity())));
        }

        invoice.setTotalAmount(total);
        Invoice saveInvoice = invoiceRepo.save(invoice);

        // Nội dung email;
        String subject = "Xác nhận đặt tour thành công #" + invoice.getId();
        String customerEmail = user.getEmail();
        NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringBuilder html = new StringBuilder();
        html.append("<html><head>")
                .append("<style>")
                .append("table { width: 100%; border-collapse: collapse; margin-top: 16px; }")
                .append("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }")
                .append("th { background-color: #f2f2f2; }")
                .append("tr:nth-child(even) { background-color: #f9f9f9; }")
                .append(".total-row td { font-weight: bold; text-align: right; }")
                .append("</style>")
                .append("</head><body>");

        html.append("<h2>Chào ").append(user.getFullname()).append(",</h2>");
        html.append("<p>Bạn đã đặt thành công các tour sau:</p>");

        html.append("<table>");
        html.append("<thead><tr>")
                .append("<th>Tên tour</th>")
                .append("<th>Ngày đi</th>")
                .append("<th>Ngày về</th>")
                .append("<th>Thành viên tham gia</th>")
                .append("<th>Số vé</th>")
                .append("<th>Giá 1 vé</th>")
                .append("<th>Thành tiền</th>")
                .append("</tr></thead>");
        html.append("<tbody>");

        BigDecimal grandTotal = BigDecimal.ZERO;

        for (TourOrderDto order : request.getTours()) {
            Tour tour = tourRepo.findById(order.getTourId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tour"));

            BigDecimal price = tour.getPrice();
            BigDecimal totalMoney = price.multiply(BigDecimal.valueOf(order.getQuantity()));
            grandTotal = grandTotal.add(totalMoney);
            String formattedTotal = currencyFormatter.format(totalMoney);

            StringBuilder memberInfo = new StringBuilder();
            for (PassengerDto p : order.getPassengers()) {
                memberInfo.append(p.getFullname())
                        .append(" (").append(p.getEmail())
                        .append(", ").append(p.getPhoneNumber()).append(")<br>");
            }

            html.append("<tr>")
                    .append("<td>").append(tour.getTourRoute().getRouteName()).append("</td>")
                    .append("<td>").append(tour.getDepatureDate().format(formatter)).append("</td>")
                    .append("<td>").append(tour.getReturnDate().format(formatter)).append("</td>")
                    .append("<td>").append(memberInfo).append("</td>")
                    .append("<td>").append(order.getQuantity()).append("</td>")
                    .append("<td>").append(currencyFormatter.format(price)).append(" đ</td>")
                    .append("<td>").append(formattedTotal).append(" đ</td>")
                    .append("</tr>");
        }
        String formattedgrandTotal = currencyFormatter.format(grandTotal);

        html.append("<tr class='total-row'><td colspan='6'>Tổng tiền</td>")
                .append("<td>").append(formattedgrandTotal).append(" đ</td></tr>");

        html.append("</tbody></table>");

        // Ghi chú thêm về thanh toán
        html.append("<p style='margin-top: 16px; color: red;'>")
                .append("<strong>Lưu ý:</strong> Vui lòng đến công ty MyTour và thanh toán trước ngày ")
                .append(saveInvoice.getCreatedAt().plusDays(2).format(formatter))
                .append(" để giữ chỗ. Nếu không, đơn hàng sẽ bị hủy tự động.")
                .append("</p>");

        html.append("<p>Phương thức thanh toán: <strong>")
                .append(invoice.getPaymentMethod()).append("</strong></p>");
        html.append("<p>Trạng thái thanh toán: <strong>")
                .append(invoice.getPaymentStatus() ? "ĐÃ THANH TOÁN" : "CHỜ THANH TOÁN")
                .append("</strong></p>");

        html.append("<br><p>Trân trọng,<br>Hệ thống My Tour</p>");
        html.append("</body></html>");


        // Gửi email
        emailService.sendOrderConfirmation(customerEmail, subject, html.toString());

    }
}

