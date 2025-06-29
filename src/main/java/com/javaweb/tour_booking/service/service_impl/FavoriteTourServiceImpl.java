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
import java.util.Optional;
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
        // Check uniqueness
        boolean exists = favoriteTourRepository
                .findByUserIdAndTourRouteId(favoriteTourDTO.getUserID(), favoriteTourDTO.getTourRouteId())
                .isPresent();
        if (exists) {
            // You can throw a custom exception here if preferred
            return null;
        }
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

    // If you have an update method, add a similar uniqueness check there:
    public FavoriteTourDTO updateFavoriteTour(Long id, FavoriteTourDTO favoriteTourDTO) {
        Optional<FavoriteTour> existing = favoriteTourRepository.findById(id);
        if (existing.isEmpty()) return null;

        // Check for uniqueness, excluding the current record
        boolean exists = favoriteTourRepository
                .findByUserIdAndTourRouteId(favoriteTourDTO.getUserID(), favoriteTourDTO.getTourRouteId())
                .filter(fav -> !fav.getId().equals(id))
                .isPresent();
        if (exists) {
            return null;
        }

        User user = userRepository.findById(favoriteTourDTO.getUserID()).orElse(null);
        TourRoute tourRoute = tourRouteRepository.findById(favoriteTourDTO.getTourRouteId()).orElse(null);
        if (user == null || tourRoute == null) return null;

        FavoriteTour favoriteTour = existing.get();
        favoriteTour.setUser(user);
        favoriteTour.setTourRoute(tourRoute);
        // set other fields as needed

        FavoriteTour saved = favoriteTourRepository.save(favoriteTour);
        return FavotiteTourMapper.mapToFavoriteTourDTO(saved);
    }
}