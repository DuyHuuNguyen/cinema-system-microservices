package com.james.paymentservice.util;

import org.springframework.stereotype.Component;

@Component("TRANSFER")
public class TransferTransaction implements Transaction {
  @Override
  public void process() {
    System.out.println("Processing transfer...");
  }
}
