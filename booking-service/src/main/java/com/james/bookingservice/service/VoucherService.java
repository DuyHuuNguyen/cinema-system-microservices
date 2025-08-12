package com.james.bookingservice.service;

import com.james.bookingservice.entity.Voucher;
import java.util.Optional;

public interface VoucherService {
  Optional<Voucher> findVoucherById(Long id);
}
