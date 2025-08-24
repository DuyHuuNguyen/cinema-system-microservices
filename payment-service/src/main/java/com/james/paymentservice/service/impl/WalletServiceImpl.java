package com.james.paymentservice.service.impl;

import com.james.paymentservice.reporitory.WalletRepository;
import com.james.paymentservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
}
