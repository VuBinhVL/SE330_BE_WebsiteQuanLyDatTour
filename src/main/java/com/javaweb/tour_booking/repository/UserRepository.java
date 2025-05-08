package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
