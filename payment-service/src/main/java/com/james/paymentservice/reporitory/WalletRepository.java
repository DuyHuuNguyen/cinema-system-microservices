package com.james.paymentservice.reporitory;

import com.james.paymentservice.entity.Wallet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
  Optional<Wallet> findByUserIdAndId(Long ownerId, Long id);

  List<Wallet> findWalletsByUserId(Long userId);
}
