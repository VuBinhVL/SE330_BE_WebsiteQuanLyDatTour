package com.javaweb.tour_booking.service;

import com.javaweb.tour_booking.dto.response.CategoryResponse;
import com.javaweb.tour_booking.entity.Category;
import com.javaweb.tour_booking.entity.UserMember;

import java.util.List;

public interface ICategoryService {
    public List<CategoryResponse> getAllCategories();
}
