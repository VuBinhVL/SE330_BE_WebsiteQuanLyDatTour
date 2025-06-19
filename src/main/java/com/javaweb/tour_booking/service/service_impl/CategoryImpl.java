package com.javaweb.tour_booking.service.service_impl;

import com.javaweb.tour_booking.dto.response.CategoryResponse;
import com.javaweb.tour_booking.entity.Category;
import com.javaweb.tour_booking.repository.CategoryRepository;
import com.javaweb.tour_booking.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryImpl implements ICategoryService {
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> new CategoryResponse(
                category.getId(),
                category.getName()
        )).collect(Collectors.toList());
    }
}
