package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TourDTO;
import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.dto.response.PopularTourRouteResponse;
import com.javaweb.tour_booking.dto.response.TourBookingDetailResponse;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourBookingDetail;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.entity.UserMember;
import com.javaweb.tour_booking.exception.tour.TourCannotBeDeletedException;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.TourRouteMapper;
import com.javaweb.tour_booking.repository.TourBookingDetailRepository;
import com.javaweb.tour_booking.repository.TourRepository;
import com.javaweb.tour_booking.repository.TourRouteRepository;
import com.javaweb.tour_booking.service.ITourService;
import com.javaweb.tour_booking.mapper.TourMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourServiceImpl implements ITourService {
    private final TourRepository tourRepository;
    private final TourRouteRepository tourRouteRepository;
    private final TourBookingDetailRepository tourBookingDetailRepository;

    @Override
    public List<TourDTO> GetAllTours() {
        List<Tour> tour = tourRepository.findAll();
        return tour.stream()
                .map(TourMapper::mapToTourDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TourDTO GetTourById(Long id) {
        Tour tour = tourRepository.getById(id);
        if (tour == null) {
            throw new TouristAttractionNotFound("Không tìm thấy chuyến du lịch");
        }
        return TourMapper.mapToTourDTO(tour);
    }

    @Override
    public TourDTO CreateTour(TourDTO newTour) {
        if (newTour == null || newTour.getTourRouteId() == null) {
            throw new IllegalArgumentException("TourDTO hoặc tourRouteId không được null");
        }

        // Lấy TourRoute từ tourRouteId
        TourRoute tourRoute = tourRouteRepository.findById(newTour.getTourRouteId())
                .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy tuyến du lịch với ID: " + newTour.getTourRouteId()));

        // Ánh xạ TourDTO sang Tour
        Tour tour = TourMapper.mapToTour(newTour, tourRoute);
        // Lưu vào cơ sở dữ liệu
        Tour saved = tourRepository.save(tour);
        // Ánh xạ ngược lại sang TourDTO
        return TourMapper.mapToTourDTO(saved);
    }

    @Override
    public TourDTO UpdateTour(long id, TourDTO updatedTour) {
        // Tìm Tour theo id
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy chuyến du lịch với ID: " + id));

        // Cập nhật tourRoute nếu tourRouteId được cung cấp
        if (updatedTour.getTourRouteId() != null) {
            TourRoute tourRoute = tourRouteRepository.findById(updatedTour.getTourRouteId())
                    .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy tuyến du lịch với ID: " + updatedTour.getTourRouteId()));
            tour.setTourRoute(tourRoute);
        }

        // Cập nhật các thuộc tính của Tour từ TourDTO
        tour.setDepatureDate(updatedTour.getDepatureDate());
        tour.setReturnDate(updatedTour.getReturnDate());
        tour.setPickUpLocation(updatedTour.getPickUpLocation());
        tour.setPickUpTime(updatedTour.getPickUpTime());
        tour.setStatus(updatedTour.getStatus());
        tour.setPrice(updatedTour.getPrice());
        tour.setTotalSeats(updatedTour.getTotalSeats());
        tour.setBookedSeats(updatedTour.getBookedSeats());
        tour.setUpdatedAt(LocalDateTime.now()); // Cập nhật thời gian sửa đổi
        // Không cập nhật createdAt vì nó không nên thay đổi

        // Lưu Tour đã cập nhật
        Tour saved = tourRepository.save(tour);

        // Ánh xạ sang TourDTO và trả về
        return TourMapper.mapToTourDTO(saved);
    }

    @Override
    public void DeleteTour(long id) {
        Tour tour = tourRepository.findById(id).orElse(null);
        if (tour == null) {
            throw new TouristAttractionNotFound("Không tìm thấy chuyến du lịch");
        }
        if (tour.getBookedSeats() > 0) {
            throw new TourCannotBeDeletedException("Không thể xóa chuyến du lịch vì đã có ghế được đặt");
        }
        tourRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourBookingDetailResponse> getListTourBookingDetailByTourId(Long tourId) {
        // Lấy danh sách TourBookingDetail theo tourId
        List<TourBookingDetail> tourBookingDetails = tourBookingDetailRepository.findByTourBookingTourId(tourId);

        // Chuyển đổi từ entity sang DTO
        return tourBookingDetails.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }

    // Hàm chuyển đổi từ entity sang DTO
    private TourBookingDetailResponse convertToResponse(TourBookingDetail tourBookingDetail) {
        TourBookingDetailResponse response = new TourBookingDetailResponse();
        response.setId(tourBookingDetail.getId());
        response.setUserFullname(tourBookingDetail.getTourBooking().getUser().getFullname());
        UserMember userMember = tourBookingDetail.getUserMember();
        response.setUserMemberFullname(userMember.getFullname());
        response.setUserMemberSex(userMember.getSex());
        response.setUserMemberPhoneNumber(userMember.getPhoneNumber());
        response.setUserMemberEmail(userMember.getEmail());
        response.setTourBookingId(tourBookingDetail.getTourBooking().getId());
        return response;
    }
    @Override
    @Transactional(readOnly = true)
    public List<PopularTourRouteResponse> getTop5PopularTourRoutes() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<Object[]> results = tourRouteRepository.findTop5PopularTourRoutes(sixMonthsAgo);

        return results.stream()
                .map(result -> {
                    Long id = ((Number) result[0]).longValue();
                    String routeName = (String) result[1];
                    String startLocation = (String) result[2];
                    String endLocation = (String) result[3];
                    Long totalBookedSeats = result[4] != null ? ((Number) result[4]).longValue() : 0L;
                    BigDecimal latestPrice = result[5] != null ? new BigDecimal(result[5].toString()) : BigDecimal.ZERO;
                    String image = (String) result[6];
                    Integer durationDays = result[7] != null ? ((Number) result[7]).intValue() : 0;
                    String recentStartDatesStr = (String) result[8];

                    List<LocalDateTime> recentStartDates = recentStartDatesStr != null && !recentStartDatesStr.isEmpty()
                            ? Arrays.stream(recentStartDatesStr.split(","))
                            .map(String::trim)
                            .map(s -> LocalDateTime.parse(s.replace(" ", "T")))
                            .filter(date -> date.isAfter(LocalDateTime.now()) || date.isEqual(LocalDateTime.now()))
                            .collect(Collectors.toList())
                            : List.of();

                    return new PopularTourRouteResponse(
                            id,
                            routeName,
                            startLocation,
                            endLocation,
                            totalBookedSeats,
                            latestPrice,
                            image,
                            durationDays != null ? durationDays.longValue() : 0L,
                            recentStartDates
                    );
                })
                .filter(response -> !response.getRecentStartDates().isEmpty()) // Chỉ giữ các tuyến có ngày khởi hành trong tương lai
                .limit(5)
                .collect(Collectors.toList());
    }

}
