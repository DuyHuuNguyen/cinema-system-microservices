package com.james.paymentservice.facade.impl;

import com.james.paymentservice.facade.TransactionFacade;
import com.james.paymentservice.resquest.UpsertTransactionRequest;
import com.james.paymentservice.service.IdempotencyService;
import com.james.paymentservice.service.TransactionService;
import com.james.paymentservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade {
    private final TransactionService transactionService;
    private final IdempotencyService idempotencyService;
    private final UserService userService;

    @Override
    public void createTransaction(UpsertTransactionRequest upsertTransactionRequest) {

    }

    private void validInfoTransaction(UpsertTransactionRequest upsertTransactionRequest) {
        var isDoubleSpending = this.idempotencyService.hasIdempotencyKey(upsertTransactionRequest.getIdempotencyKey());
        if (isDoubleSpending) throw new RuntimeException("double-spending");

        switch (upsertTransactionRequest.getTransactionType()) {
            case TRANSFER -> {
                var isPaymentForBooking = upsertTransactionRequest.getIsPaymentForBooking();
                if(isPaymentForBooking) {
                    // communicate with scheduleService
                }
            }
            case DEPOSIT,WITHDRAW -> {
                // valid id user or throw exception
            }
        }


    }
}
