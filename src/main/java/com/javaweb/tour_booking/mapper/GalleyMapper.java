package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.GalleyDTO;
import com.javaweb.tour_booking.entity.Galley;
import com.javaweb.tour_booking.entity.TouristAttraction;

public class GalleyMapper {
    public static GalleyDTO mapToGalleyDTO(Galley galley) {
        GalleyDTO galleyDTO = new GalleyDTO();
        galleyDTO.setId(galley.getId());
        galleyDTO.setThumbNail(galley.getThumbNail());
        galleyDTO.setTouristAttractionId(galley.getTouristAttraction().getId());
        return galleyDTO;
    }

    public static Galley mapToGalley(GalleyDTO galleyDTO, TouristAttraction touristAttraction) {
        Galley galley = new Galley();
        galley.setId(galleyDTO.getId());
        galley.setThumbNail(galleyDTO.getThumbNail());
        galley.setTouristAttraction(touristAttraction);
        return galley;
    }
}
