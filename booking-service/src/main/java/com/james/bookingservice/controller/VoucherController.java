package com.james.bookingservice.controller;

import com.james.bookingservice.facade.VoucherFacade;
import com.james.bookingservice.request.UpsertVoucherRequest;
import com.james.bookingservice.request.VoucherCriteria;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.PaginationResponse;
import com.james.bookingservice.response.VoucherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/vouchers")
@RestController
public class VoucherController {
  private final VoucherFacade voucherFacade;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Voucher APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> createVoucher(@Valid @RequestBody UpsertVoucherRequest request) {
    this.voucherFacade.createVoucher(request);
    return BaseResponse.ok();
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Voucher APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> updateVoucher(
      @PathVariable Long id, @Valid @RequestBody UpsertVoucherRequest request) {
    request.withId(id);
    this.voucherFacade.updateVoucher(request);
    return BaseResponse.ok();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Voucher APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<PaginationResponse<VoucherResponse>> findAll(
      @NonNull VoucherCriteria criteria) {
    return this.voucherFacade.findAll(criteria);
  }
}
