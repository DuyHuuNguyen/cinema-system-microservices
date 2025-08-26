package com.james.paymentservice.service.impl;

import com.james.paymentservice.dto.TransactionDeadLetterDTO;
import com.james.paymentservice.dto.TransactionSuccessfulDTO;
import com.james.paymentservice.entity.Transaction;
import com.james.paymentservice.entity.Wallet;
import com.james.paymentservice.enums.ErrorCode;
import com.james.paymentservice.enums.TransactionEnum;
import com.james.paymentservice.enums.TransactionType;
import com.james.paymentservice.exception.EntityNotFoundException;
import com.james.paymentservice.resquest.TransactionSuccessNotificationRequest;
import com.james.paymentservice.service.NotificationService;
import com.james.paymentservice.service.PaymentSuccessConsumer;
import com.james.paymentservice.service.TransactionService;
import com.james.paymentservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentSuccessfulConsumerImpl implements PaymentSuccessConsumer {
  private final WalletService walletService;
  private final TransactionService transactionService;
  private final NotificationService notificationService;
  private final KafkaTemplate<String, TransactionDeadLetterDTO>
      kafkaTemplateForDeadLetterTransaction;

  @Value("${kafka.var.topic-successful-transaction}")
  private String TOPIC_SUCCESSFUL;

  @Value("${kafka.var.topic-deal-letter-transaction}")
  private String TOPIC_DEAL_LETTER;

  @Override
  @KafkaListener(
      topics = "${kafka.var.topic-successful-transaction}",
      groupId = "${kafka.var.group-id-handle-successful}")
  public void handleSuccessTransaction(TransactionSuccessfulDTO transactionSuccessfulDTO) {
    Wallet sourceWallet = null;
    Wallet destinationWallet = null;
    try {
      sourceWallet =
          this.walletService
              .findById(transactionSuccessfulDTO.getSourceWalletId())
              .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
      destinationWallet =
          this.walletService
              .findById(transactionSuccessfulDTO.getDestinationWalletId())
              .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PAYMENT_NOT_FOUND));

      sourceWallet.minusBalance(transactionSuccessfulDTO.getAmount());
      destinationWallet.plusBalance(transactionSuccessfulDTO.getAmount());
    } catch (EntityNotFoundException e) {
      log.info("Wallet could not be found {}", transactionSuccessfulDTO);
      var transactionDeadLetterDTO =
          TransactionDeadLetterDTO.builder()
              .transactionType(transactionSuccessfulDTO.getTransactionType())
              .amount(transactionSuccessfulDTO.getAmount())
              .destinationWalletId(transactionSuccessfulDTO.getDestinationWalletId())
              .sourceWalletId(transactionSuccessfulDTO.getSourceWalletId())
              .message("Wallet could not be found")
              .build();
      kafkaTemplateForDeadLetterTransaction.send(TOPIC_DEAL_LETTER, transactionDeadLetterDTO);
      return;
    }
    var transaction =
        Transaction.builder()
            .amount(transactionSuccessfulDTO.getAmount())
            .type(TransactionType.TRANSFER)
            .destinationWallet(destinationWallet)
            .sourceWallet(sourceWallet)
            .status(TransactionEnum.SUCCESS)
            .build();
    var newTransaction = this.transactionService.save(transaction);
    log.info("Successfully processed transaction");

    var transactionSuccessNotificationRequest =
        TransactionSuccessNotificationRequest.builder()
            .transactionId(newTransaction.getId())
            .build();
    try {
      this.notificationService.sendNotification(transactionSuccessNotificationRequest);
    } catch (Exception e) {
      log.info("Notification could not be sent {}", transactionSuccessNotificationRequest);
    }
  }
}
