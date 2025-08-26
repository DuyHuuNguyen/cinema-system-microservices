package com.james.paymentservice.util;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionFactory {

  private final Map<String, Transaction> transactionMap;

  //        public TransactionFactory(Map<String, Transaction> transactionMap) {
  //            this.transactionMap = transactionMap;
  //        }
  //    @PostConstruct
  //    void run(){
  //        log.info("Starting TransactionFactory {} ", transactionMap);
  //    }
  //
  //        public Transaction getTransaction(TransactionType type) {
  //            Transaction transaction = transactionMap.get(type.name());
  //            if (transaction == null) {
  //                throw new IllegalArgumentException("No transaction found for type: " + type);
  //            }
  //            return transaction;
  //        }

}
