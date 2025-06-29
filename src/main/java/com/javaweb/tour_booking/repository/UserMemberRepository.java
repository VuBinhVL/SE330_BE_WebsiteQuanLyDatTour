package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.UserMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserMemberRepository extends JpaRepository<UserMember, Long> {
    List<UserMember> findByUserId(Long userId);
    Optional<UserMember> findByEmailAndPhoneNumber(String email, String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
