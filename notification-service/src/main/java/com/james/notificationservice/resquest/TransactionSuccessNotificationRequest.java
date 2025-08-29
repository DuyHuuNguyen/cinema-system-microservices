package com.james.notificationservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionSuccessNotificationRequest {
    private Long transactionId;
    private Long sourceWalletId;
    private Long destinationWalletId;
    private Double amount;
}
