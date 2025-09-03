package com.james.paymentservice.service;

import com.james.paymentservice.entity.Wallet;
import java.util.Optional;

public interface WalletService {
  Optional<Wallet> findById(Long id);

  Optional<Wallet> findByUserIdAndId(Long userId, Long id);

  void save(Wallet wallet);
}
