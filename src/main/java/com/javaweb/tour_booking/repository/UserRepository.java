package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Account;
import com.javaweb.tour_booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByRoleId(Long roleId);
    Optional<User> findByIdAndRoleId(Long id, Long roleId);
    Optional<User> findByAccount(Account account);
    Optional<User> findByEmail(String email);
}
