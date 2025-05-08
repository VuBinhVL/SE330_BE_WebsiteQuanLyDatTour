package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
