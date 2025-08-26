package com.james.paymentservice.service.impl;

import com.james.paymentservice.entity.Wallet;
import com.james.paymentservice.reporitory.WalletRepository;
import com.james.paymentservice.service.WalletService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
  private final WalletRepository walletRepository;

  @Override
  public Optional<Wallet> findById(Long id) {
    return walletRepository.findById(id);
  }
}
