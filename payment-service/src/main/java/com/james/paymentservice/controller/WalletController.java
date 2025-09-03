package com.james.paymentservice.controller;

import com.james.paymentservice.facade.WalletFacade;
import com.james.paymentservice.response.BaseResponse;
import com.james.paymentservice.response.WalletResponse;
import com.james.paymentservice.resquest.UpsertWalletRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {
  private final WalletFacade walletFacade;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(tags = {"Wallet APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> createWallet(@RequestBody UpsertWalletRequest upsertWalletRequest) {
    this.walletFacade.createWallet(upsertWalletRequest);
    return BaseResponse.ok();
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Wallet APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> updateWallet(
      @PathVariable Long id, @RequestBody UpsertWalletRequest upsertWalletRequest) {
    upsertWalletRequest.withId(id);
    this.walletFacade.updateWallet(upsertWalletRequest);
    return BaseResponse.ok();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Wallet APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<List<WalletResponse>> findAll() {
    return this.walletFacade.findAll();
  }
}
