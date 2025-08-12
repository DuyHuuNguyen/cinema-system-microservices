package com.james.bookingservice.service.impl;

import com.james.bookingservice.entity.Voucher;
import com.james.bookingservice.repository.VoucherRepository;
import com.james.bookingservice.service.VoucherService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
  private final VoucherRepository voucherRepository;

  @Override
  public Optional<Voucher> findVoucherById(Long id) {
    return voucherRepository.findById(id);
  }
}
