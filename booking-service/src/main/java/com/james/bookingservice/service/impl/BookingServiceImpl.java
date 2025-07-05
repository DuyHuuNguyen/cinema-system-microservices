package com.james.bookingservice.service.impl;

import com.james.bookingservice.repository.BookingRepository;
import com.james.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
  private final BookingRepository bookingRepository;
}
