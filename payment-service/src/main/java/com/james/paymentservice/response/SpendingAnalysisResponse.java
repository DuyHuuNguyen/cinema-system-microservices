package com.james.paymentservice.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SpendingAnalysisResponse {
  private Integer transactionTimes;
  private List<Long> transactionIds;
  private Double transactionAmounts;
}
