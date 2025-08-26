package com.james.paymentservice.service.impl;

import com.james.paymentservice.dto.TransactionCreateDTO;
import com.james.paymentservice.service.CheckinPaymentCreatedProducer;
import com.james.paymentservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckinPaymentCreatedProducerImpl implements CheckinPaymentCreatedProducer {
  private final WalletService walletService;
  private final KafkaTemplate<String, TransactionCreateDTO> kafkaTemplate;

  @Value("${kafka.var.topic-create-transaction}")
  private String TOPIC_CREATE_TRANSACTION;

  @Override
  public void createTransaction(TransactionCreateDTO transactionCreateDTO) {
    log.info("Create transaction {}", transactionCreateDTO);
    this.kafkaTemplate.send(TOPIC_CREATE_TRANSACTION, transactionCreateDTO);
  }
}
