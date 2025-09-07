package com.james.paymentservice.facade;

import com.james.paymentservice.dto.TransactionDTO;
import com.james.paymentservice.response.*;
import com.james.paymentservice.resquest.CreateTransactionInternalForBookingTicket;
import com.james.paymentservice.resquest.CreateTransactionRequest;
import com.james.paymentservice.resquest.SpendingTimeRangeRequest;
import com.james.paymentservice.resquest.TransactionCriteria;

public interface TransactionFacade {
  void createTransaction(CreateTransactionRequest ticketTransactionRequest);

  BaseResponse<TransactionDetailResponse> findTransactionDetailById(Long id);

  BaseResponse<PaginationResponse<TransactionResponse>> findByFilter(
      TransactionCriteria transactionCriteria);

  BaseResponse<SpendingAnalysisResponse> findByAnalysisTimeRange(
      SpendingTimeRangeRequest spendingTimeRangeRequest);

  Boolean verifyTransaction(Long id);

  TransactionDTO findTransactionById(Long id);

  Long createTransactionInternal(
      CreateTransactionInternalForBookingTicket createTransactionInternalForBookingTicket);
}
