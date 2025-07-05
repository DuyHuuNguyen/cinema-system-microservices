package com.james.bookingservice.facade.impl;

import com.james.bookingservice.facade.BookingFacade;
import com.james.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {
  private final BookingService bookingService;
}
