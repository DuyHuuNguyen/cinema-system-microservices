package com.james.bookingservice.facade.impl;

import com.james.bookingservice.config.SecurityUserDetails;
import com.james.bookingservice.dto.VoucherDTO;
import com.james.bookingservice.entity.Voucher;
import com.james.bookingservice.enums.ErrorCode;
import com.james.bookingservice.exception.EntityNotFoundException;
import com.james.bookingservice.exception.PermissionDeniedException;
import com.james.bookingservice.facade.VoucherFacade;
import com.james.bookingservice.request.UpsertVoucherRequest;
import com.james.bookingservice.request.ValidAdminTheaterRequest;
import com.james.bookingservice.request.VoucherCriteria;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.PaginationResponse;
import com.james.bookingservice.response.VoucherResponse;
import com.james.bookingservice.service.ScheduleService;
import com.james.bookingservice.service.VoucherService;
import com.james.bookingservice.specification.VoucherSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VoucherFacadeImpl implements VoucherFacade {
  private final VoucherService voucherService;
  private final ScheduleService scheduleService;

  @Override
  @Transactional
  public void createVoucher(UpsertVoucherRequest request) {
    this.verifyAdminTheater(request.getTheaterId());
    var voucher =
        Voucher.builder()
            .voucherType(request.getVoucherType().trim())
            .percent(request.getPercent())
            .maxPrice(request.getMaxPrice())
            .expiredAt(request.getExpiredAt())
            .voucherCode(request.getVoucherCode().trim())
            .description(request.getDescription())
            .theaterId(request.getTheaterId())
            .quality(request.getQuality())
            .isActive(true)
            .build();
    this.voucherService.save(voucher);
  }

  @Override
  @Transactional
  public void updateVoucher(UpsertVoucherRequest request) {
    this.verifyAdminTheater(request.getTheaterId());
    var voucher =
        this.voucherService
            .findVoucherById(request.getId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND));

    var isValidVoucherOwner = voucher.getTheaterId() == request.getTheaterId();
    if (!isValidVoucherOwner) throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED);
    var voucherDTO =
        VoucherDTO.builder()
            .voucherType(request.getVoucherType())
            .percent(request.getPercent())
            .maxPrice(request.getMaxPrice())
            .expiredAt(request.getExpiredAt())
            .voucherCode(request.getVoucherCode())
            .description(request.getDescription())
            .quality(request.getQuality())
            .build();

    voucher.changeInfo(voucherDTO);
    this.voucherService.save(voucher);
  }

  @Override
  public BaseResponse<PaginationResponse<VoucherResponse>> findAll(VoucherCriteria criteria) {

    Specification<Voucher> specification =
        VoucherSpecification.hasTheaterId(criteria.getTheaterId());

    Pageable pageable = PageRequest.of(criteria.getCurrentPage(), criteria.getPageSize());

    var pages = this.voucherService.findAll(specification, pageable);

    List<VoucherResponse> voucherResponses =
        pages
            .get()
            .map(
                voucher ->
                    VoucherResponse.builder()
                        .id(voucher.getId())
                        .voucherType(voucher.getVoucherType())
                        .percent(voucher.getPercent())
                        .maxPrice(voucher.getMaxPrice())
                        .expiredAt(voucher.getExpiredAt())
                        .voucherCode(voucher.getVoucherCode())
                        .description(voucher.getDescription())
                        .theaterId(voucher.getTheaterId())
                        .quality(voucher.getQuality())
                        .createdAt(voucher.getCreatedAt())
                        .build())
            .toList();

    var response =
        PaginationResponse.<VoucherResponse>builder()
            .data(voucherResponses)
            .currentPage(criteria.getCurrentPage())
            .totalPages(pages.getTotalPages())
            .totalElements(pages.getNumberOfElements())
            .build();

    return BaseResponse.build(response, true);
  }

  private void verifyAdminTheater(Long theaterId) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    try {
      var validAdminTheaterRequest =
          ValidAdminTheaterRequest.builder()
              .adminId(principal.getId())
              .theaterId(theaterId)
              .build();

      var isValidAdmin = this.scheduleService.validAdminTheater(validAdminTheaterRequest);
      if (!isValidAdmin) throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED);

    } catch (Exception e) {
      throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED);
    }
  }
}
