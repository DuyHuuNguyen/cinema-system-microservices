package com.james.paymentservice.service.impl;

import com.james.paymentservice.dto.TransactionSuccessfulDTO;
import com.james.paymentservice.entity.Transaction;
import com.james.paymentservice.enums.TransactionEnum;
import com.james.paymentservice.enums.TransactionType;
import com.james.paymentservice.service.PaymentSuccessConsumer;
import com.james.paymentservice.service.TransactionService;
import com.james.paymentservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentSuccessfulConsumerImpl implements PaymentSuccessConsumer {
  private final WalletService walletService;
  private final TransactionService transactionService;

  @Value("${kafka.var.topic-successful-transaction}")
  private String TOPIC_SUCCESSFUL;

  @Override
  @KafkaListener(
      topics = "${kafka.var.topic-successful-transaction}",
      groupId = "${kafka.var.group-id-handle-successful}")
  public void handleSuccessTransaction(TransactionSuccessfulDTO transactionSuccessfulDTO) {
    var sourceWallet =
        this.walletService.findById(transactionSuccessfulDTO.getSourceWalletId()).orElseThrow();
    var destinationWallet =
        this.walletService
            .findById(transactionSuccessfulDTO.getDestinationWalletId())
            .orElseThrow();

    sourceWallet.minusBalance(transactionSuccessfulDTO.getAmount());
    destinationWallet.plusBalance(transactionSuccessfulDTO.getAmount());

    var transaction =
        Transaction.builder()
            .amount(transactionSuccessfulDTO.getAmount())
            .type(TransactionType.TRANSFER)
            .destinationWallet(destinationWallet)
            .sourceWallet(sourceWallet)
            .status(TransactionEnum.SUCCESS)
            .build();
    this.transactionService.save(transaction);
    log.info("Successfully processed transaction");
  }
}
