package com.james.bookingservice.facade.impl;

import com.james.bookingservice.config.SecurityUserDetails;
import com.james.bookingservice.entity.Booking;
import com.james.bookingservice.enums.ErrorCode;
import com.james.bookingservice.exception.PermissionDeniedException;
import com.james.bookingservice.facade.DashBoardFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.BookingResponse;
import com.james.bookingservice.response.PaginationResponse;
import com.james.bookingservice.resquest.BookingCriteria;
import com.james.bookingservice.resquest.ValidAdminTheaterRequest;
import com.james.bookingservice.service.BookingService;
import com.james.bookingservice.service.ScheduleService;
import com.james.bookingservice.specification.BookingSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DashboardFacadeImpl implements DashBoardFacade {
  private final BookingService bookingService;
  private final ScheduleService scheduleService;

  @Override
  public BaseResponse<PaginationResponse<BookingResponse>> findByFilter(BookingCriteria criteria) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    try {
      var validAdminTheaterRequest =
          ValidAdminTheaterRequest.builder()
              .adminId(principal.getId())
              .theaterId(criteria.getTheaterId())
              .build();

      var isValidAdmin = this.scheduleService.validAdminTheater(validAdminTheaterRequest);
      if (!isValidAdmin) throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED);

    } catch (Exception e) {
      throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED);
    }

    Specification<Booking> specification =
        BookingSpecification.hasTheaterId(criteria.getTheaterId())
            .and(BookingSpecification.hasAroundCreatedAt(criteria.getAroundCreatedAt()));

    var pageable =
        PageRequest.of(
            criteria.getCurrentPage(),
            criteria.getPageSize(),
            Sort.by(Sort.Direction.ASC, "createdAt"));
    var pages = this.bookingService.findAll(specification, pageable);

    var response =
        PaginationResponse.<BookingResponse>builder()
            .data(
                pages
                    .get()
                    .map(
                        booking ->
                            BookingResponse.builder()
                                .id(booking.getId())
                                .bookingCode(booking.getBookingCode())
                                .userId(booking.getId())
                                .paymentId(booking.getPaymentId())
                                .theaterId(booking.getTheaterId())
                                .createdAt(booking.getCreatedAt())
                                .build())
                    .toList())
            .currentPage(criteria.getCurrentPage())
            .totalPages(pages.getTotalPages())
            .totalElements(pages.getNumberOfElements())
            .build();

    return BaseResponse.build(response, true);
  }
}
