package com.james.paymentservice.service.impl;

import com.james.paymentservice.dto.TransactionDeadLetterDTO;
import com.james.paymentservice.resquest.TransactionFailNotificationRequest;
import com.james.paymentservice.service.NotificationService;
import com.james.paymentservice.service.PaymentDeadLetterConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentDeadLetterConsumerImpl implements PaymentDeadLetterConsumer {
  private final NotificationService notificationService;

  @Override
  @KafkaListener(
      topics = "${kafka.var.topic-deal-letter-transaction}",
      groupId = "${kafka.var.group-id-handle-fail}")
  public void handleFailTransaction(TransactionDeadLetterDTO transactionDeadLetterDTO) {
    var transactionFailNotificationRequest =
        TransactionFailNotificationRequest.builder()
            .principalId(transactionDeadLetterDTO.getPrincipalId())
            .transactionType(transactionDeadLetterDTO.getTransactionType())
            .sourceWalletId(transactionDeadLetterDTO.getSourceWalletId())
            .destinationWalletId(transactionDeadLetterDTO.getDestinationWalletId())
            .amount(transactionDeadLetterDTO.getAmount())
            .build();
    try {
      this.notificationService.sendNotification(transactionFailNotificationRequest);
    } catch (Exception e) {
      log.info(
          "Notify fail : TransactionFailNotificationRequest: {}",
          transactionFailNotificationRequest);
    }
    log.info("Notify : TransactionFailNotificationRequest: {}", transactionFailNotificationRequest);
  }
}
