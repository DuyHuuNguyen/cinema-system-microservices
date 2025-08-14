package com.james.bookingservice.service.impl;

import com.james.bookingservice.entity.Booking;
import com.james.bookingservice.repository.BookingRepository;
import com.james.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
  private final BookingRepository bookingRepository;

  @Override
  public Booking save(Booking booking) {
    return this.bookingRepository.save(booking);
  }

  @Override
  public void delete(Booking booking) {
    this.bookingRepository.delete(booking);
  }

  @Override
  public void addPaymentIdForBookingById(Long id, Long paymentId) {
    this.bookingRepository.addPaymentIdForBookingById(id, paymentId);
  }

  @Override
  public Page<Booking> findAll(Specification<Booking> specification, Pageable pageable) {
    return this.bookingRepository.findAll(specification, pageable);
  }
}
