package com.james.bookingservice.facade.impl;

import com.james.bookingservice.config.SecurityUserDetails;
import com.james.bookingservice.dto.FoodDTO;
import com.james.bookingservice.dto.PaymentDTO;
import com.james.bookingservice.dto.TicketDTO;
import com.james.bookingservice.entity.Booking;
import com.james.bookingservice.enums.ErrorCode;
import com.james.bookingservice.exception.EntityNotFoundException;
import com.james.bookingservice.exception.PermissionDeniedException;
import com.james.bookingservice.facade.DashBoardFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.BookingDetailResponse;
import com.james.bookingservice.response.BookingResponse;
import com.james.bookingservice.response.PaginationResponse;
import com.james.bookingservice.resquest.BookingCriteria;
import com.james.bookingservice.resquest.ValidAdminTheaterRequest;
import com.james.bookingservice.service.BookingService;
import com.james.bookingservice.service.PaymentService;
import com.james.bookingservice.service.ScheduleService;
import com.james.bookingservice.specification.BookingSpecification;
import java.util.ArrayList;
import java.util.List;
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
  private final PaymentService paymentService;

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

  @Override
  public BaseResponse<BookingDetailResponse> findBookingDetailById(Long id) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var booking =
        this.bookingService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.BOOKING_NOT_FOUND));
    PaymentDTO paymentDTO = null;
    try {
      paymentDTO = this.paymentService.findPaymentById(booking.getPaymentId());
    } catch (Exception e) {
    }

    List<FoodDTO> foodDTOs = new ArrayList<>();
    try {
      for (var bookingFood : booking.getBookingFingerFoods()) {
        var food = this.scheduleService.findFoodById(bookingFood.getFingerFoodId());
        foodDTOs.add(food);
      }
    } catch (Exception e) {
      throw new EntityNotFoundException(ErrorCode.FOOD_NOT_FOUND);
    }

    var bookingDetailResponse =
        BookingDetailResponse.builder()
            .id(booking.getId())
            .userId(booking.getUserId())
            .bookingCode(booking.getBookingCode())
            .paymentDTO(paymentDTO)
            .ticketDTOs(
                booking.getTickets().stream()
                    .map(
                        ticket ->
                            TicketDTO.builder()
                                .id(ticket.getId())
                                .scheduleId(ticket.getScheduleId())
                                .seatCode(ticket.getSeatCode())
                                .seatNumber(ticket.getSeatNumber())
                                .price(ticket.getPrice())
                                .build())
                    .toList())
            .foodDTOs(foodDTOs)
            .theaterId(booking.getTheaterId())
            .createdAt(booking.getCreatedAt())
            .paymentId(booking.getPaymentId())
            .build();

    return BaseResponse.build(bookingDetailResponse, true);
  }
}
