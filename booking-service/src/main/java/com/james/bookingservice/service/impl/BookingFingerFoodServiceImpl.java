package com.james.bookingservice.service.impl;

import com.james.bookingservice.repository.BookingFingerFoodRepository;
import com.james.bookingservice.service.BookingFingerFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingFingerFoodServiceImpl implements BookingFingerFoodService {
  private final BookingFingerFoodRepository bookingFingerFoodRepository;
}
