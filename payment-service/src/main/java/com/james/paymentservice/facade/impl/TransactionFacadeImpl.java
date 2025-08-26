package com.james.paymentservice.facade.impl;

import com.james.paymentservice.config.SecurityUserDetails;
import com.james.paymentservice.dto.TransactionCreateDTO;
import com.james.paymentservice.enums.ErrorCode;
import com.james.paymentservice.exception.DoubleSpendingException;
import com.james.paymentservice.facade.TransactionFacade;
import com.james.paymentservice.resquest.CreateTransactionRequest;
import com.james.paymentservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade {
  private final TransactionService transactionService;
  private final IdempotencyService idempotencyService;
  private final UserService userService;
  private final BookingService bookingService;
  private final WalletService walletService;

  private final CheckinPaymentCreatedProducer checkinPaymentCreatedProducer;

  @Override
  public void createTransaction(CreateTransactionRequest createTransactionRequest) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var isDuplicationSpendingRequest =
        idempotencyService.hasIdempotencyKey(createTransactionRequest.getIdempotencyKey());
    if (isDuplicationSpendingRequest) throw new DoubleSpendingException(ErrorCode.DOUBLE_SPENDING);

    var transactionCreateDTO =
        TransactionCreateDTO.builder()
            .principalId(principal.getId())
            .amount(createTransactionRequest.getAmount())
            .transactionType(createTransactionRequest.getTransactionType())
            .destinationWalletId(createTransactionRequest.getPartnerId())
            .sourceWalletId(createTransactionRequest.getWalletId())
            .build();
    this.checkinPaymentCreatedProducer.createTransaction(transactionCreateDTO);
    log.info("Create transaction");
  }
}
