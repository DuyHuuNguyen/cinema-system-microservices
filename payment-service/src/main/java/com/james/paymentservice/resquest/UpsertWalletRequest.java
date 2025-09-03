package com.james.paymentservice.resquest;

import com.james.paymentservice.enums.WalletStatus;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpsertWalletRequest {
  @Hidden private Long id;
  @NotNull private String walletName;
  @NotNull private String currency;
  private WalletStatus walletStatus;

  public void withId(Long id) {
    this.id = id;
  }
}
