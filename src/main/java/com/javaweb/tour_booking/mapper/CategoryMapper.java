package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.CategoryDTO;
import com.javaweb.tour_booking.entity.Category;

public class CategoryMapper {
    public static CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static Category mapToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }

}
