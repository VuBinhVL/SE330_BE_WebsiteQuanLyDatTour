package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourBookingDetailDTO;
import com.javaweb.tour_booking.entity.*;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.InvoiceMapper;
import com.javaweb.tour_booking.mapper.TourBookingDetailMapper;
import com.javaweb.tour_booking.mapper.TourMapper;
import com.javaweb.tour_booking.repository.TourBookingDetailRepository;
import com.javaweb.tour_booking.repository.TourBookingRepository;
import com.javaweb.tour_booking.repository.UserMemberRepository;
import com.javaweb.tour_booking.service.ITourBookingDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourBookingDetailServiceImpl implements ITourBookingDetailService {
    private final TourBookingDetailRepository tourBookingDetailRepository;
    private final UserMemberRepository userMemberRepository;
    private final TourBookingRepository tourBookingRepository;
    @Override
    public TourBookingDetailDTO CreateTourBookingDetail(TourBookingDetailDTO tourBookingDetailDTO) {
        if (tourBookingDetailDTO == null || tourBookingDetailDTO.getTourBookingId() == null ||tourBookingDetailDTO.getUserMemberId() == null) {
            throw new IllegalArgumentException("TourDTO hoặc tourBookingId hoặc userMemberId không được null");
        }
        UserMember userMember = userMemberRepository.findById(tourBookingDetailDTO.getUserMemberId())
                .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy User Member với ID: " + tourBookingDetailDTO.getUserMemberId()));
        TourBooking tourBooking = tourBookingRepository.findById(tourBookingDetailDTO.getTourBookingId())
                .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy Tour Booking với ID: " + tourBookingDetailDTO.getTourBookingId()));
        TourBookingDetail tourBookingDetail = TourBookingDetailMapper.mapToTourBookingDetail(tourBookingDetailDTO,userMember,tourBooking,true);
        TourBookingDetail saved = tourBookingDetailRepository.save(tourBookingDetail);
        return TourBookingDetailMapper.mapToTourBookingDetailDTO(saved);
    }

    @Override
    public List<TourBookingDetailDTO> getAllTourBookingDetails() {
        List<TourBookingDetail> entities = tourBookingDetailRepository.findAll();
        return entities.stream()
                .map(TourBookingDetailMapper::mapToTourBookingDetailDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TourBookingDetailDTO> getByBookingId(Long bookingId) {
        List<TourBookingDetail> details = tourBookingDetailRepository.findByTourBookingId(bookingId);
        return details.stream()
                .map(TourBookingDetailMapper::mapToTourBookingDetailDTO)
                .collect(Collectors.toList());
    }

}
