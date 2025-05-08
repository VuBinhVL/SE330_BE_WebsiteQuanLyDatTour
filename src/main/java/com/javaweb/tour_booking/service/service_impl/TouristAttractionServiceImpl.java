package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.TouristAttractionDTO;
import com.javaweb.tour_booking.entity.TouristAttraction;
import com.javaweb.tour_booking.exception.tourist_attraction.TouristAttractionNotFound;
import com.javaweb.tour_booking.mapper.TouristAttractionMapper;
import com.javaweb.tour_booking.repository.TouristAttractionRepository;
import com.javaweb.tour_booking.service.ITouristAttractionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TouristAttractionServiceImpl implements ITouristAttractionService {
    private TouristAttractionRepository touristAttractionRepository;
    @Override
    public List<TouristAttractionDTO> getAllTouristAttractions() {
        List<TouristAttraction> touristAttractionList = touristAttractionRepository.findAll();
        return touristAttractionList.stream().map((
                touristAttraction -> TouristAttractionMapper.mapToTouristAttractionDTO(touristAttraction)))
                .collect(Collectors.toList());
    }

    @Override
    public TouristAttractionDTO createTouristAttraction(TouristAttractionDTO touristAttractionDTO) {
        TouristAttraction touristAttraction = TouristAttractionMapper.mapToTouristAttraction(touristAttractionDTO);
        TouristAttraction saveTouristAttraction = touristAttractionRepository.save(touristAttraction);
        return TouristAttractionMapper.mapToTouristAttractionDTO(saveTouristAttraction);
    }

    @Override
    public TouristAttractionDTO getTouristAttractionById(Long id) {
        TouristAttraction touristAttraction = touristAttractionRepository.findById(id).orElse(null);
        if (touristAttraction == null) {
            throw new TouristAttractionNotFound("Không tìm thấy địa điểm du lịch");
        }
        return TouristAttractionMapper.mapToTouristAttractionDTO(touristAttraction);
    }

    @Override
    public TouristAttractionDTO updateTouristAttraction(Long id, TouristAttractionDTO touristAttractionDTO) {
        TouristAttraction touristAttraction = touristAttractionRepository.findById(id).orElse(null);
        if (touristAttraction == null) {
            throw new TouristAttractionNotFound("Không tìm thấy địa điểm du lịch");
        }
        touristAttraction.setName(touristAttractionDTO.getName());
        touristAttraction.setDescription(touristAttractionDTO.getDescription());
        touristAttraction.setLocation(touristAttractionDTO.getLocation());
        touristAttraction.setCategory(touristAttractionDTO.getCategory());
        touristAttraction.setCreatedAt(touristAttractionDTO.getCreatedAt());
        touristAttraction.setUpdatedAt(touristAttractionDTO.getUpdatedAt());
        TouristAttraction saveTouristAttraction = touristAttractionRepository.save(touristAttraction);
        return TouristAttractionMapper.mapToTouristAttractionDTO(saveTouristAttraction);
    }

    @Override
    public void deleteTouristAttraction(Long id) {
        TouristAttraction touristAttraction = touristAttractionRepository.findById(id).orElse(null);
        if (touristAttraction == null) {
            throw new TouristAttractionNotFound("Không tìm thấy địa điểm du lịch");
        }
        touristAttractionRepository.deleteById(id);
    }


}
