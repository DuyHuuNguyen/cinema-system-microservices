package com.james.paymentservice.util;

import org.springframework.stereotype.Component;

@Component("WITHDRAW")
public class WithdrawTransaction implements Transaction {
  @Override
  public void process() {
    System.out.println("Processing withdraw...");
  }
}
