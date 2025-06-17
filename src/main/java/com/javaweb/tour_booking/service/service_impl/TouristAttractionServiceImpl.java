package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TouristAttractionDTO;
import com.javaweb.tour_booking.dto.response.TouristAttractionDetailResponse;
import com.javaweb.tour_booking.dto.response.TouristAttractionResponse;
import com.javaweb.tour_booking.entity.Galley;
import com.javaweb.tour_booking.entity.TouristAttraction;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.TouristAttractionMapper;
import com.javaweb.tour_booking.repository.GalleyRepository;
import com.javaweb.tour_booking.repository.TouristAttractionRepository;
import com.javaweb.tour_booking.service.ITouristAttractionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TouristAttractionServiceImpl implements ITouristAttractionService {
    private TouristAttractionRepository touristAttractionRepository;
    private GalleyRepository galleyRepository;

    @Override
    public List<TouristAttractionResponse> getAllTouristAttractions() {
        return touristAttractionRepository.findAll().stream()
                .map(attraction -> new TouristAttractionResponse(
                        attraction.getId(),
                        attraction.getName(),
                        attraction.getLocation(),
                        attraction.getCategory().getName()
                )).collect(Collectors.toList());
    }

    @Override
    public TouristAttractionDetailResponse getTouristAttractionById(Long id) {
        TouristAttraction touristAttraction = touristAttractionRepository.findById(id)
                .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy địa điểm du lịch"));

        //Lấy danh sách ảnh của địa điểm
        List<Galley> galleyList = galleyRepository.findByTouristAttraction(touristAttraction);
        List <String> imageURL = galleyList.stream().map(Galley::getThumbNail).collect(Collectors.toList());
        return new TouristAttractionDetailResponse(
                touristAttraction.getId(),
                touristAttraction.getName(),
                touristAttraction.getLocation(),
                touristAttraction.getCategory().getName(),
                touristAttraction.getCategory().getId(),
                touristAttraction.getDescription(),
                imageURL
        );

    }

    @Transactional
    @Override
    public void deleteTouristAttractionById(Long id) {
        TouristAttraction attraction = touristAttractionRepository.findById(id)
                .orElseThrow(() -> new TouristAttractionNotFound("Không tìm thấy địa điểm du lịch"));

        //Xóa ảnh
        galleyRepository.deleteByTouristAttraction(attraction);

        //Xóa địa điểm
        touristAttractionRepository.delete(attraction);
    }
}
