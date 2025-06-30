package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.FavoriteRouteDTO;
import com.javaweb.tour_booking.dto.response.AttractionInfo;
import com.javaweb.tour_booking.dto.response.TourInfo;
import com.javaweb.tour_booking.dto.response.TourRouteDetailResponse;
import com.javaweb.tour_booking.dto.response.TourRouteSearchResponse;
import com.javaweb.tour_booking.entity.*;
import com.javaweb.tour_booking.dto.TourRouteDTO;
import com.javaweb.tour_booking.entity.Tour;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.exception.tour_route.TourRouteCannotBeDeletedException;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.TourRouteMapper;
import com.javaweb.tour_booking.repository.*;
import com.javaweb.tour_booking.service.ITourRouteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TourRouteServiceImpl implements ITourRouteService {
    private final TourRouteRepository tourRouteRepository;
    private final TourRepository tourRepository;
    private final TourRouteAttractionRepository attractionRepo;
    private final FavoriteTourRepository favoriteRepo;
    private final GalleyRepository galleryRepo;

    private String getInitials(String str) {
        if (str == null || str.isBlank()) return "";
        return Arrays.stream(str.trim().split("\\s+"))
                .filter(s -> !s.isEmpty())
                .map(s -> s.substring(0, 1).toUpperCase())
                .collect(Collectors.joining());
    }

    @Override
    public List<TourRouteDTO> getAllTourRoutes() {
        List<TourRoute> tourRoutes = tourRouteRepository.findAll();
        return tourRoutes.stream()
                .map(TourRouteMapper::mapToTourRouteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TourRouteDTO createTourRoute(TourRouteDTO tourRouteDTO) {
        TourRoute tourRoute = TourRouteMapper.mapToTourRoute(tourRouteDTO);
        TourRoute saved = tourRouteRepository.save(tourRoute);
        return TourRouteMapper.mapToTourRouteDTO(saved);
    }

    @Override
    public TourRouteDTO getTourRouteById(Long id) {
        TourRoute tourRoute = tourRouteRepository.findById(id).orElse(null);
        if (tourRoute == null) {
            throw new TouristAttractionNotFound("Không tìm thấy tuyến du lịch");
        }
        return TourRouteMapper.mapToTourRouteDTO(tourRoute);
    }

    @Override
    public TourRouteDTO updateTourRoute(Long id, TourRouteDTO tourRouteDTO) {
        TourRoute tourRoute = tourRouteRepository.findById(id).orElse(null);
        if (tourRoute == null) {
            throw new TouristAttractionNotFound("Không tìm thấy tuyến du lịch");
        }
        tourRoute.setRouteName(tourRouteDTO.getRouteName());
        tourRoute.setStartLocation(tourRouteDTO.getStartLocation());
        tourRoute.setEndLocation(tourRouteDTO.getEndLocation());
        tourRoute.setImage(tourRouteDTO.getImage());
        tourRoute.setStartDate(tourRouteDTO.getStartDate());
        tourRoute.setEndDate(tourRouteDTO.getEndDate());
        tourRoute.setCreatedAt(tourRouteDTO.getCreatedAt());
        tourRoute.setUpdatedAt(tourRouteDTO.getUpdatedAt());
        TourRoute saved = tourRouteRepository.save(tourRoute);
        return TourRouteMapper.mapToTourRouteDTO(saved);
    }

    @Override
    public void deleteTourRoute(Long id) {
        TourRoute tourRoute = tourRouteRepository.findById(id).orElse(null);
        if (tourRoute == null) {
            throw new TouristAttractionNotFound("Không tìm thấy tuyến du lịch");
        }
        // Kiểm tra xem có Tour nào liên kết với TourRoute này không
        List<Tour> tours = tourRepository.findAll().stream()
                .filter(tour -> tour.getTourRoute().getId().equals(id))
                .collect(Collectors.toList());
        if (!tours.isEmpty()) {
            throw new TourRouteCannotBeDeletedException("Không thể xóa tuyến du lịch vì vẫn còn tour liên kết với tuyến này");
        }
        tourRouteRepository.deleteById(id);
    }

    @Override
    public List<FavoriteRouteDTO> getTop4FavoriteRoutes() {
        List<FavoriteRouteDTO> favoriteRoutes = tourRouteRepository.findFavoriteRoutes();
        return favoriteRoutes.stream().limit(4).collect(Collectors.toList());
    }

    @Override
    public List<TourRouteSearchResponse> searchTourRoutes() {
        LocalDateTime now = LocalDateTime.now();

        // Get all tour routes
        List<TourRoute> tourRoutes = tourRouteRepository.findAll();

        // Get all tours with status = 0 and depatureDate >= today
        List<Tour> activeTours = tourRepository.findAll().stream()
                .filter(tour -> tour.getStatus() == 0 && !tour.getDepatureDate().isBefore(now))
                .collect(Collectors.toList());

        // Group tours by tourRouteId
        Map<Long, List<Tour>> toursByRoute = activeTours.stream()
                .collect(Collectors.groupingBy(tour -> tour.getTourRoute().getId()));

        List<TourRouteSearchResponse> responses = new ArrayList<>();

        for (TourRoute route : tourRoutes) {
            List<Tour> tours = toursByRoute.getOrDefault(route.getId(), Collections.emptyList());
            if (tours.isEmpty()) continue; // Skip if no active tours

            // Sort tours by depatureDate ascending
            tours.sort(Comparator.comparing(Tour::getDepatureDate));

            // Get the tour with the nearest depatureDate
            Tour nearestTour = tours.get(0);

            // Build code: startLocation + endLocation + routeId
            // Usage in your loop:
            String code = getInitials(route.getStartLocation()) +
                    getInitials(route.getEndLocation()) +
                    route.getId();

            // Calculate duration and nights
            int duration = (int) java.time.Duration.between(
                    nearestTour.getDepatureDate().toLocalDate().atStartOfDay(),
                    nearestTour.getReturnDate().toLocalDate().atStartOfDay()
            ).toDays() + 1;
            int nights = duration - 1;

            // Collect all start dates for this route
            List<LocalDate> startDates = tours.stream()
                    .map(t -> t.getDepatureDate().toLocalDate())
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            responses.add(new TourRouteSearchResponse(
                    route.getId(),
                    route.getRouteName(),
                    code,
                    duration,
                    nights,
                    nearestTour.getPrice(),
                    route.getStartLocation(),
                    route.getEndLocation(),
                    startDates,
                    route.getImage()
            ));
        }

        return responses;
    }

    //Lấy thông tin chi tiét
    @Override
    public TourRouteDetailResponse getDetail(Long routeId, Long userId) {
        TourRoute route = tourRouteRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tour route"));

        boolean isFavorite = userId != null && favoriteRepo.existsByUserIdAndTourRouteId(userId, routeId);

        // Tours
        List<Tour> tours = tourRepository.findByTourRouteId(routeId).stream()
                .filter(t -> t.getStatus() == 0)
                .toList();;
        List<TourInfo> tourInfos = tours.stream().map(t -> {
            long duration = ChronoUnit.DAYS.between(t.getDepatureDate(), t.getReturnDate());
            List<Long> attractionIds = attractionRepo.findByTourRouteId(routeId).stream()
                    .map(a -> a.getTouristAttraction().getId()).distinct().toList();
            List<String> tourGalleries = galleryRepo.findByTouristAttractionIdIn(attractionIds).stream()
                    .map(Galley::getThumbNail).toList();
            return new TourInfo(
                    t.getId(),
                    t.getDepatureDate().toLocalDate(),
                    t.getReturnDate().toLocalDate(),
                    t.getPrice(),
                    t.getTotalSeats(),
                    t.getBookedSeats(),
                    t.getPickUpLocation(),
                    t.getPickUpTime().toString(),
                    duration,
                    tourGalleries
            );
        }).toList();

        // Itinerary
        List<AttractionInfo> itinerary = attractionRepo.findByTourRouteIdOrderByDayAscOrderActionAsc(routeId).stream()
                .map(a -> {
                    List<String> imgs = galleryRepo.findByTouristAttractionId(a.getTouristAttraction().getId())
                            .stream().map(Galley::getThumbNail).toList();
                    return new AttractionInfo(
                            a.getDay(),
                            a.getOrderAction(),
                            a.getTouristAttraction().getName(),
                            imgs
                    );
                }).toList();

        return new TourRouteDetailResponse(
                route.getId(),
                route.getRouteName(),
                route.getStartLocation(),
                route.getEndLocation(),
                isFavorite,
                tourInfos,
                itinerary
        );
    }


}