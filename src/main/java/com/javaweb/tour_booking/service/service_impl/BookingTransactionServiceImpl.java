package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.InvoiceDTO;
import com.javaweb.tour_booking.dto.TourBookingDTO;
import com.javaweb.tour_booking.dto.TourBookingDetailDTO;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.service.IBookingTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingTransactionServiceImpl implements IBookingTransactionService {
    private final InvoiceServiceImpl invoiceService;
    private final TourBookingServiceImpl tourBookingService;
    private final TourBookingDetailServiceImpl tourBookingDetailService;
    private final TourRepository tourRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createBookingTransaction(InvoiceDTO invoiceDTO, TourBookingDTO tourBookingDTO, TourBookingDetailDTO tourBookingDetailDTO) {
        //Tạo invoice
        InvoiceDTO createdInvoice = invoiceService.CreateInvoice(invoiceDTO);
        tourBookingDTO.setInvoiceId(createdInvoice.getId());

        //Tạo tour booking
        TourBookingDTO createdTourBooking = tourBookingService.createTourBooking(tourBookingDTO);
        tourBookingDetailDTO.setTourBookingId(createdTourBooking.getId());

        //Cập nhật bookedSeats trong Tour
        Tour tour = tourRepository.findById(tourBookingDTO.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        tour.setBookedSeats(tour.getBookedSeats() + tourBookingDTO.getSeatsBooked());
        tourRepository.save(tour);

        // Tạo tour booking detail
        tourBookingDetailService.CreateTourBookingDetail(tourBookingDetailDTO);
    }
}