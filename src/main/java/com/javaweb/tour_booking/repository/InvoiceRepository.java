package com.javaweb.tour_booking.repository;

import com.javaweb.tour_booking.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
