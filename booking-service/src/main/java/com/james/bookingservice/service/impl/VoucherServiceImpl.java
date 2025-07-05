package com.james.bookingservice.service.impl;

import com.james.bookingservice.repository.VoucherRepository;
import com.james.bookingservice.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
  private final VoucherRepository voucherRepository;
}
