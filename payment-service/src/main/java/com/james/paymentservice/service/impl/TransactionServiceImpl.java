package com.james.paymentservice.service.impl;

import com.james.paymentservice.reporitory.TransactionRepository;
import com.james.paymentservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
}
