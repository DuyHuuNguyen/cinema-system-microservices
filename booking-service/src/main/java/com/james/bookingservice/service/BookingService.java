package com.james.bookingservice.service;

import com.james.bookingservice.entity.Booking;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface BookingService {
  Booking save(Booking booking);

  void delete(Booking booking);

  void addPaymentIdForBookingById(Long id, Long paymentId);

  Page<Booking> findAll(Specification<Booking> specification, Pageable pageable);

  Optional<Booking> findByIdAndOwnerId(Long id, Long ownerId);
}
