package com.james.paymentservice.facade.impl;

import com.james.paymentservice.config.SecurityUserDetails;
import com.james.paymentservice.entity.Wallet;
import com.james.paymentservice.enums.ErrorCode;
import com.james.paymentservice.enums.WalletStatus;
import com.james.paymentservice.exception.EntityNotFoundException;
import com.james.paymentservice.facade.WalletFacade;
import com.james.paymentservice.response.BaseResponse;
import com.james.paymentservice.response.WalletResponse;
import com.james.paymentservice.resquest.UpsertWalletRequest;
import com.james.paymentservice.service.WalletService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletFacadeImpl implements WalletFacade {
  private final WalletService walletService;

  @Override
  @Transactional
  public void createWallet(UpsertWalletRequest upsertWalletRequest) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var wallet =
        Wallet.builder()
            .walletName(upsertWalletRequest.getWalletName())
            .status(WalletStatus.ACTIVE)
            .userId(principal.getId())
            .currency(upsertWalletRequest.getCurrency())
            .build();
    walletService.save(wallet);
  }

  @Override
  @Transactional
  public void updateWallet(UpsertWalletRequest upsertWalletRequest) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    var wallet =
        this.walletService
            .findByUserIdAndId(principal.getId(), upsertWalletRequest.getId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.WALLET_NOT_FOUND));
    wallet.changeWalletName(upsertWalletRequest.getWalletName());
    wallet.changeStatus(upsertWalletRequest.getWalletStatus());
    this.walletService.save(wallet);
  }

  @Override
  public BaseResponse<List<WalletResponse>> findAll() {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<Wallet> wallets = this.walletService.findAllByOwnerId(principal.getId());

    var walletResponse =
        wallets.stream()
            .map(
                wallet ->
                    WalletResponse.builder()
                        .id(wallet.getId())
                        .userId(wallet.getUserId())
                        .balance(wallet.getBalance())
                        .currency(wallet.getCurrency())
                        .walletName(wallet.getWalletName())
                        .status(wallet.getStatus())
                        .build())
            .toList();
    return BaseResponse.build(walletResponse, true);
  }
}
