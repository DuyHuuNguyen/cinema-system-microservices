package com.james.paymentservice.entity;

import com.james.paymentservice.enums.WalletStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@SuperBuilder
@Table(name = "wallets")
public class Wallet extends BaseEntity {
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "wallet_name")
  private String walletName;

  @Column(name = "balance")
  private Double balance;

  @Column(name = "currency")
  private String currency;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private WalletStatus status;
}
