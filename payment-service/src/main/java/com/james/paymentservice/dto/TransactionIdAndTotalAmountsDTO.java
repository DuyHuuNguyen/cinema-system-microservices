package com.james.paymentservice.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionIdAndTotalAmountsDTO {
  private String transactionIds;
  private BigDecimal totalAmounts;
}
