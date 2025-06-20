package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.UserMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserMemberRepository extends JpaRepository<UserMember, Long> {
    List<UserMember> findByUserId(Long userId);
}
