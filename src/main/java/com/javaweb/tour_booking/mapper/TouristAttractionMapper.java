package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.TouristAttractionDTO;
import com.javaweb.tour_booking.entity.TouristAttraction;

public class TouristAttractionMapper {
    public static TouristAttractionDTO mapToTouristAttractionDTO (TouristAttraction touristAttraction)
    {
        TouristAttractionDTO attractionDTO = new TouristAttractionDTO();
        attractionDTO.setId(touristAttraction.getId());
        attractionDTO.setName(touristAttraction.getName());
        attractionDTO.setDescription(touristAttraction.getDescription());
        attractionDTO.setLocation(touristAttraction.getLocation());
        attractionDTO.setCategory(touristAttraction.getCategory());
        attractionDTO.setCreatedAt(touristAttraction.getCreatedAt());
        attractionDTO.setUpdatedAt(touristAttraction.getUpdatedAt());
        return attractionDTO;
    }

    public static TouristAttraction mapToTouristAttraction (TouristAttractionDTO touristAttractionDTO)
    {
        TouristAttraction touristAttraction = new TouristAttraction();
        touristAttraction.setId(touristAttractionDTO.getId());
        touristAttraction.setName(touristAttractionDTO.getName());
        touristAttraction.setDescription(touristAttractionDTO.getDescription());
        touristAttraction.setLocation(touristAttractionDTO.getLocation());
        touristAttraction.setCategory(touristAttractionDTO.getCategory());
        touristAttraction.setCreatedAt(touristAttractionDTO.getCreatedAt());
        touristAttraction.setUpdatedAt(touristAttractionDTO.getUpdatedAt());
        return touristAttraction;
    }
}
