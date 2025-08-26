package com.james.paymentservice.facade.impl;

import com.james.paymentservice.dto.TransactionCreateDTO;
import com.james.paymentservice.enums.ErrorCode;
import com.james.paymentservice.exception.DoubleSpendingException;
import com.james.paymentservice.facade.TransactionFacade;
import com.james.paymentservice.resquest.TicketTransactionRequest;
import com.james.paymentservice.service.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  public void createTransaction(TicketTransactionRequest ticketTransactionRequest) {
    log.info("Create transaction");
    var isDuplicationSpendingRequest =
        idempotencyService.hasIdempotencyKey(ticketTransactionRequest.getIdempotencyKey());
    if (isDuplicationSpendingRequest) throw new DoubleSpendingException(ErrorCode.DOUBLE_SPENDING);

    var transactionCreateDTO =
        TransactionCreateDTO.builder()
            .amount(ticketTransactionRequest.getAmount())
            .transactionType(ticketTransactionRequest.getTransactionType())
            .destinationWalletId(ticketTransactionRequest.getPartnerId())
            .sourceWalletId(ticketTransactionRequest.getWalletId())
            .build();
    this.checkinPaymentCreatedProducer.createTransaction(transactionCreateDTO);
  }

  @PostConstruct
  void aa() {
    var sourceWallet = this.walletService.findById(1L);
    System.out.println(sourceWallet);
  }

  //    private void validInfoTransaction(TicketTransactionRequest upsertTransactionRequest) {
  //        var isDoubleSpending =
  // this.idempotencyService.hasIdempotencyKey(upsertTransactionRequest.getIdempotencyKey());
  //        if (isDoubleSpending) throw new RuntimeException("double-spending");
  //
  //        switch (upsertTransactionRequest.getTransactionType()) {
  //            case TRANSFER -> {
  //                var isPaymentForBooking = upsertTransactionRequest.getIsPaymentForBooking();
  //                if(isPaymentForBooking) {
  //                    // communicate with scheduleService
  //                }
  //            }
  //            case DEPOSIT,WITHDRAW -> {
  //                // valid id user or throw exception
  //            }
  //        }
  //
  //
  //    }
}
