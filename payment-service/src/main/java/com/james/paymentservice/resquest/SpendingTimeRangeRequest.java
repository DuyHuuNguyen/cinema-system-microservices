package com.james.paymentservice.resquest;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SpendingTimeRangeRequest {
  @NotNull private Long walletId;
  @NotNull private Long from;
  @NotNull private Long to;
}
