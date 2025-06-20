package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.FavoriteTourDTO;
import com.javaweb.tour_booking.dto.response.FavTourResponse;
import com.javaweb.tour_booking.entity.FavoriteTour;
import com.javaweb.tour_booking.entity.TourRoute;
import com.javaweb.tour_booking.entity.User;
import com.javaweb.tour_booking.mapper.FavotiteTourMapper;
import com.javaweb.tour_booking.repository.FavoriteTourRepository;
import com.javaweb.tour_booking.repository.TourRouteRepository;
import com.javaweb.tour_booking.repository.UserRepository;
import com.javaweb.tour_booking.service.IFavoriteTourService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavoriteTourServiceImpl implements IFavoriteTourService {
    private final FavoriteTourRepository favoriteTourRepository;
    private final UserRepository userRepository;
    private final TourRouteRepository tourRouteRepository;

    @Override
    public List<FavoriteTourDTO> getAllFavoriteTours() {
        return favoriteTourRepository.findAll().stream()
                .map(FavotiteTourMapper::mapToFavoriteTourDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FavoriteTourDTO getFavoriteTourById(Long id) {
        return favoriteTourRepository.findById(id)
                .map(FavotiteTourMapper::mapToFavoriteTourDTO)
                .orElse(null);
    }

    @Override
    public FavoriteTourDTO addFavoriteTour(FavoriteTourDTO favoriteTourDTO) {
        User user = userRepository.findById(favoriteTourDTO.getUserID()).orElse(null);
        TourRoute tourRoute = tourRouteRepository.findById(favoriteTourDTO.getTourRouteId()).orElse(null);
        if (user == null || tourRoute == null) return null;
        FavoriteTour favoriteTour = FavotiteTourMapper.mapToFavoriteTour(favoriteTourDTO, tourRoute, user);
        FavoriteTour saved = favoriteTourRepository.save(favoriteTour);
        return FavotiteTourMapper.mapToFavoriteTourDTO(saved);
    }

    @Override
    public void removeFavoriteTour(Long id) {
        favoriteTourRepository.deleteById(id);
    }

    @Override
    public List<FavoriteTourDTO> getFavoriteToursByUserId(Long userId) {
        return favoriteTourRepository.findByUserId(userId).stream()
                .map(FavotiteTourMapper::mapToFavoriteTourDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FavTourResponse> getFavoriteToursResponseByUserId(Long userId) {
        return favoriteTourRepository.findByUserId(userId).stream()
                .map(favoriteTour -> {
                    TourRoute tourRoute = favoriteTour.getTourRoute();
                    User user = favoriteTour.getUser();
                    return new FavTourResponse(
                            favoriteTour.getId(),
                            favoriteTour.getCreatedAt(),
                            user.getId(),
                            tourRoute.getId(),
                            tourRoute.getRouteName(),
                            user.getFullname()
                    );
                })
                .collect(Collectors.toList());
    }
}