package com.james.paymentservice.util;

import org.springframework.stereotype.Component;

@Component("DEPOSIT")
public class DepositTransaction implements Transaction {
  @Override
  public void process() {
    System.out.println("Processing deposit...");
  }
}
