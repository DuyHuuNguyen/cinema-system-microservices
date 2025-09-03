package com.james.paymentservice.controller;

import com.james.paymentservice.facade.TransactionFacade;
import com.james.paymentservice.response.BaseResponse;
import com.james.paymentservice.response.PaginationResponse;
import com.james.paymentservice.response.TransactionDetailResponse;
import com.james.paymentservice.response.TransactionResponse;
import com.james.paymentservice.resquest.CreateTransactionRequest;
import com.james.paymentservice.resquest.TransactionCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
      @RequestBody CreateTransactionRequest createTransactionRequest) {
    this.transactionFacade.createTransaction(createTransactionRequest);
    return BaseResponse.ok();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Transaction APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<TransactionDetailResponse> findTransactionDetailById(@PathVariable Long id) {
    return this.transactionFacade.findTransactionDetailById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Transaction APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<PaginationResponse<TransactionResponse>> findByFilter(
      @NotNull TransactionCriteria transactionCriteria) {
    return this.transactionFacade.findByFilter(transactionCriteria);
  }
}
