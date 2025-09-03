package com.james.paymentservice.facade;

import com.james.paymentservice.response.BaseResponse;
import com.james.paymentservice.response.WalletResponse;
import com.james.paymentservice.resquest.UpsertWalletRequest;
import java.util.List;

public interface WalletFacade {
  void createWallet(UpsertWalletRequest upsertWalletRequest);

  void updateWallet(UpsertWalletRequest upsertWalletRequest);

  BaseResponse<List<WalletResponse>> findAll();
}
