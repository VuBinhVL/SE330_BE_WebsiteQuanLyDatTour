package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
