package com.james.bookingservice.service;

import com.james.bookingservice.entity.Booking;

public interface BookingService {
  Booking save(Booking booking);

  void delete(Booking booking);

  void addPaymentIdForBookingById(Long id, Long paymentId);
}
