package com.james.bookingservice.service;

import com.james.bookingservice.entity.Voucher;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface VoucherService {
  Optional<Voucher> findVoucherById(Long id);

  void save(Voucher voucher);

  Page<Voucher> findAll(Specification<Voucher> specification, Pageable pageable);
}
