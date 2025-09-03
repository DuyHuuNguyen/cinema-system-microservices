package com.james.paymentservice.response;

import com.james.paymentservice.enums.WalletStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class WalletResponse {
  private Long id;
  private Long userId;
  private String walletName;
  private Double balance;
  private String currency;
  private WalletStatus status;
}
