package com.james.paymentservice.controller;

import com.james.paymentservice.facade.TransactionFacade;
import com.james.paymentservice.response.BaseResponse;
import com.james.paymentservice.resquest.UpsertTransactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionFacade transactionFacade;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Transaction APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> createTransaction(
      @RequestBody UpsertTransactionRequest upsertTransactionRequest) {
    this.transactionFacade.createTransaction(upsertTransactionRequest);
    return BaseResponse.ok();
  }
}
