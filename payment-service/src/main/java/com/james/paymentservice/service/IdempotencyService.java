package com.james.paymentservice.service;

public interface IdempotencyService {
  void storage(String idempotencyKey);

  Boolean hasIdempotencyKey(String idempotencyKey);
}
