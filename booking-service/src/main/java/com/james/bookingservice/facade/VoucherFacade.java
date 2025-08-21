package com.james.bookingservice.facade;

import com.james.bookingservice.request.UpsertVoucherRequest;
import com.james.bookingservice.request.VoucherCriteria;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.PaginationResponse;
import com.james.bookingservice.response.VoucherResponse;

public interface VoucherFacade {
  void createVoucher(UpsertVoucherRequest request);

  void updateVoucher(UpsertVoucherRequest request);

  BaseResponse<PaginationResponse<VoucherResponse>> findAll(VoucherCriteria criteria);
}
