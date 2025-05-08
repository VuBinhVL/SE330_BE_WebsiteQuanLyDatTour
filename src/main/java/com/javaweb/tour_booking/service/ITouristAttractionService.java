package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.TouristAttractionDTO;

import java.util.List;

public interface ITouristAttractionService {
    //Lấy hết tất cả các địa điểm du lịch
    public List<TouristAttractionDTO> getAllTouristAttractions();

    //Tạo mới 1 điểm du lịch
    public TouristAttractionDTO createTouristAttraction(TouristAttractionDTO touristAttractionDTO);

    //Lấy thông tin 1 điềm du lịch
    public TouristAttractionDTO getTouristAttractionById(Long id);

    //Update thông tin điểm du lịch
    public TouristAttractionDTO updateTouristAttraction(Long id, TouristAttractionDTO touristAttractionDTO);

    //Xóa địa điểm du lịch
    public void deleteTouristAttraction(Long id);
}
