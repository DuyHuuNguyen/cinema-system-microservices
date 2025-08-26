package com.james.paymentservice.facade;

import com.james.paymentservice.resquest.TicketTransactionRequest;

public interface TransactionFacade {
  void createTransaction(TicketTransactionRequest ticketTransactionRequest);
}
