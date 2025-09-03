package com.james.paymentservice.facade;

import com.james.paymentservice.resquest.UpsertWalletRequest;

public interface WalletFacade {
  void createWallet(UpsertWalletRequest upsertWalletRequest);

  void updateWallet(UpsertWalletRequest upsertWalletRequest);
}
