package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.request.InvoiceRequest;
import com.javaweb.tour_booking.dto.request.PassengerDto;
import com.javaweb.tour_booking.dto.request.TourOrderDto;
import com.javaweb.tour_booking.entity.*;
import com.javaweb.tour_booking.repository.*;
import com.javaweb.tour_booking.service.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {
    private InvoiceRepository invoiceRepo;
    private TourBookingRepository bookingRepo;
    private TourBookingDetailRepository detailRepo;
    private UserMemberRepository userMemberRepo;
    private UserRepository userRepo;
    private TourRepository tourRepo;

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
        invoiceRepo.save(invoice);
    }
}

