package com.james.paymentservice.facade;

import com.james.paymentservice.response.BaseResponse;
import com.james.paymentservice.response.PaginationResponse;
import com.james.paymentservice.response.TransactionDetailResponse;
import com.james.paymentservice.response.TransactionResponse;
import com.james.paymentservice.resquest.CreateTransactionRequest;
import com.james.paymentservice.resquest.TransactionCriteria;

public interface TransactionFacade {
  void createTransaction(CreateTransactionRequest ticketTransactionRequest);

  BaseResponse<TransactionDetailResponse> findTransactionDetailById(Long id);

  BaseResponse<PaginationResponse<TransactionResponse>> findByFilter(
      TransactionCriteria transactionCriteria);
}
