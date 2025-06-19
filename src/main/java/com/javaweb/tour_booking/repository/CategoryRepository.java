package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
