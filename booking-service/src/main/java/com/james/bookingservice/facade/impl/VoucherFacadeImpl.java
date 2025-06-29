package com.james.bookingservice.facade.impl;

import com.james.bookingservice.facade.VoucherFacade;
import com.james.bookingservice.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherFacadeImpl implements VoucherFacade {
  private final VoucherService voucherService;
}
