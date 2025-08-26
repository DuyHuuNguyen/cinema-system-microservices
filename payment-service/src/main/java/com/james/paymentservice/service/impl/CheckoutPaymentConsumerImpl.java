package com.james.paymentservice.service.impl;

import com.james.paymentservice.dto.TransactionCreateDTO;
import com.james.paymentservice.dto.TransactionDeadLetterDTO;
import com.james.paymentservice.dto.TransactionSuccessfulDTO;
import com.james.paymentservice.entity.Wallet;
import com.james.paymentservice.service.CheckoutPaymentConsumer;
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
public class CheckoutPaymentConsumerImpl implements CheckoutPaymentConsumer {
  private final WalletService walletService;
  private final KafkaTemplate<String, TransactionCreateDTO> kafkaTemplate;
  private final KafkaTemplate<String, TransactionDeadLetterDTO>
      kafkaTemplateForDeadLetterTransaction;
  private final KafkaTemplate<String, TransactionSuccessfulDTO>
      kafkaTemplateForSuccessfulTransaction;

  @Value("${kafka.var.topic-deal-letter-transaction}")
  private String TOPIC_DEAL_LETTER;

  @Value("${kafka.var.topic-successful-transaction}")
  private String TOPIC_SUCCESSFUL;

  @Override
  @KafkaListener(
      topics = "${kafka.var.topic-create-transaction}",
      groupId = "${kafka.var.group-id-transaction-coordinator}")
  public void receiveAndHandleTransaction(TransactionCreateDTO transactionCreateDTO) {
    Wallet sourceWallet = null;
    Wallet destinationWallet = null;

    sourceWallet =
        this.walletService.findById(transactionCreateDTO.getSourceWalletId()).orElse(null);
    destinationWallet =
        this.walletService.findById(transactionCreateDTO.getDestinationWalletId()).orElse(null);

    boolean isWalletsMissing = sourceWallet == null || destinationWallet == null;
    boolean isInsufficientBalance =
        sourceWallet != null && sourceWallet.getBalance() < transactionCreateDTO.getAmount();

    if (isWalletsMissing || isInsufficientBalance) {
      var transactionDeadLetterDTO =
          TransactionDeadLetterDTO.builder()
              .transactionType(transactionCreateDTO.getTransactionType())
              .amount(transactionCreateDTO.getAmount())
              .destinationWalletId(transactionCreateDTO.getDestinationWalletId())
              .sourceWalletId(transactionCreateDTO.getSourceWalletId())
              .build();
      kafkaTemplateForDeadLetterTransaction.send(TOPIC_DEAL_LETTER, transactionDeadLetterDTO);
      log.info("transaction fail -> topic deal letter {}", transactionDeadLetterDTO);
      return;
    }
    var transactionSuccessfulDTO =
        TransactionSuccessfulDTO.builder()
            .transactionType(transactionCreateDTO.getTransactionType())
            .amount(transactionCreateDTO.getAmount())
            .destinationWalletId(transactionCreateDTO.getDestinationWalletId())
            .sourceWalletId(transactionCreateDTO.getSourceWalletId())
            .build();
    kafkaTemplateForSuccessfulTransaction.send(TOPIC_SUCCESSFUL, transactionSuccessfulDTO);
    log.info("transaction valid is successful -> topic successful {}", transactionSuccessfulDTO);
  }
}
