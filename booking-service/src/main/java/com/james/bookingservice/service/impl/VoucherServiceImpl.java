package com.james.bookingservice.service.impl;

import com.james.bookingservice.entity.Voucher;
import com.james.bookingservice.repository.VoucherRepository;
import com.james.bookingservice.service.VoucherService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
  private final VoucherRepository voucherRepository;

  @Override
  public Optional<Voucher> findVoucherById(Long id) {
    return voucherRepository.findById(id);
  }

  @Override
  public void save(Voucher voucher) {
    this.voucherRepository.save(voucher);
  }

  @Override
  public Page<Voucher> findAll(Specification<Voucher> specification, Pageable pageable) {
    return this.voucherRepository.findAll(specification, pageable);
  }
}
