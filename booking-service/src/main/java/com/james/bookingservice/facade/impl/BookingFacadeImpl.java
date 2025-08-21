package com.james.bookingservice.facade.impl;

import com.james.bookingservice.config.SecurityUserDetails;
import com.james.bookingservice.dto.FoodDTO;
import com.james.bookingservice.dto.FoodIdAndQuantityDTO;
import com.james.bookingservice.dto.PaymentDTO;
import com.james.bookingservice.dto.TicketDTO;
import com.james.bookingservice.entity.Booking;
import com.james.bookingservice.entity.BookingFingerFood;
import com.james.bookingservice.enums.ErrorCode;
import com.james.bookingservice.enums.PaymentStatus;
import com.james.bookingservice.enums.PaymentType;
import com.james.bookingservice.exception.EntityNotFoundException;
import com.james.bookingservice.exception.TicketExpireException;
import com.james.bookingservice.exception.TicketUsedException;
import com.james.bookingservice.facade.BookingFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.BookingDetailResponse;
import com.james.bookingservice.response.BookingResponse;
import com.james.bookingservice.response.PaginationResponse;
import com.james.bookingservice.request.BookingCriteria;
import com.james.bookingservice.request.CreateBookingTicketRequest;
import com.james.bookingservice.request.CreatePaymentRequest;
import com.james.bookingservice.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import com.james.bookingservice.specification.BookingSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {
  private final BookingService bookingService;
  private final TicketService ticketService;
  private final VoucherService voucherService;
  private final ScheduleService scheduleService;
  private final PaymentService paymentService;


  @Override
  @Transactional
  public void createBooking(CreateBookingTicketRequest request) {
        AtomicReference<Float> totalPrice = new AtomicReference<>(0F);

      var principal =
              (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      var booking =
              Booking.builder()
                      .paymentId(-1L)
                      .userId(principal.getId())
                      .theaterId(request.getTheaterId())
                      .build();

      this.buildTicketsForBooking(booking, request.getTicketIds(), totalPrice);
      this.buildVouchersForBooking(booking, request.getVoucherIds(), totalPrice);
      this.buildFoodsForBooking(booking, request.getFoodIdAndQuantityDTOS(), totalPrice, request.getTheaterId());


      switch (request.getPaymentType()) {
          case COD -> {
              Long paymentId = null;
              booking.addBookingCode(UUID.randomUUID().toString());
              var newBooking = this.bookingService.save(booking);
              var paymentRequest =
                      CreatePaymentRequest.builder()
                              .paymentStatus(PaymentStatus.FAILURE)
                              .paymentType(PaymentType.COD)
                              .bookingId(newBooking.getId())
                              .price(totalPrice.get())
                              .build();
              log.info("req body payment {} ", paymentRequest);
              try {
                  paymentId = paymentService.createPayment(paymentRequest);
              } catch (Exception e) {
                  this.bookingService.delete(newBooking);
                  this.markTicketUnused(request.getTicketIds());
                  throw new EntityNotFoundException(ErrorCode.PAYMENT_INVALID);
              }
              this.bookingService.addPaymentIdForBookingById(newBooking.getId(), paymentId);
              log.info("booking ok by cod! 👌");
          }
          case MOMO, VN_PAY -> {
               try {
                   var isValidPayment = request.getPaymentId() != null ||  this.paymentService.verifyPayment(request.getPaymentId());
                   if (!isValidPayment) throw new EntityNotFoundException(ErrorCode.PAYMENT_INVALID);
               } catch (Exception e){
                   throw new EntityNotFoundException(ErrorCode.PAYMENT_INVALID);
               }
               booking.addBookingCode(UUID.randomUUID().toString());
               booking.addPaymentId(request.getPaymentId());
              var newBooking = this.bookingService.save(booking);
              try{
                  this.paymentService.addBookingIdForPayment(request.getPaymentId(),newBooking.getPaymentId());
              } catch (Exception e){
                  this.bookingService.delete(newBooking);
                  throw new EntityNotFoundException(ErrorCode.PAYMENT_INVALID);
              }
              log.info("booking ok by repay for ! 👌");
          }
      }

  }

    @Override
    public BaseResponse<PaginationResponse<BookingResponse>> findByFilter(BookingCriteria criteria) {
        var principal = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Specification<Booking> specification = BookingSpecification.hasTheaterId(criteria.getTheaterId())
                .and(BookingSpecification.hasAroundCreatedAt(criteria.getAroundCreatedAt()))
                .and(BookingSpecification.hasOwnerId(principal.getId()));

        var pageable = PageRequest.of(criteria.getCurrentPage(), criteria.getPageSize(), Sort.by( Sort.Direction.ASC,"createdAt"));
        var pages = this.bookingService.findAll(specification,pageable);

        var response = PaginationResponse.<BookingResponse>builder()
                .data(pages.get().map(booking -> BookingResponse.builder()
                        .id(booking.getId())
                        .bookingCode(booking.getBookingCode())
                        .userId(booking.getId())
                        .paymentId(booking.getPaymentId())
                        .theaterId(booking.getTheaterId())
                        .createdAt(booking.getCreatedAt())
                        .build()).toList())
                .currentPage(criteria.getCurrentPage())
                .totalPages(pages.getTotalPages())
                .totalElements(pages.getNumberOfElements())
                .build();

        return BaseResponse.build(
                response
                , true
        );
    }

    @Override
    public BaseResponse<BookingDetailResponse> findDetailById(Long id) {
        var principal = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var booking = this.bookingService.findByIdAndOwnerId(id, principal.getId()).orElseThrow(() -> new EntityNotFoundException(ErrorCode.BOOKING_NOT_FOUND));
        PaymentDTO paymentDTO = null;
        try {
            paymentDTO = this.paymentService.findPaymentById(booking.getPaymentId());
        } catch (Exception e) {
        }

        List<FoodDTO> foodDTOs = new ArrayList<>();
        try{
            for(var bookingFood : booking.getBookingFingerFoods()){
                var food = this.scheduleService.findFoodById(bookingFood.getFingerFoodId());
                foodDTOs.add(food);
            }
        }catch (Exception e){
            throw new EntityNotFoundException(ErrorCode.FOOD_NOT_FOUND);
        }

        var bookingDetailResponse = BookingDetailResponse.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .bookingCode(booking.getBookingCode())
                .paymentDTO(paymentDTO)
                .ticketDTOs(booking.getTickets().stream()
                        .map(ticket -> TicketDTO.builder().id(ticket.getId())
                                .scheduleId(ticket.getScheduleId())
                                .seatCode(ticket.getSeatCode())
                                .seatNumber(ticket.getSeatNumber())
                                .price(ticket.getPrice())
                                .build()).toList()
                        )
                .foodDTOs(foodDTOs)
                .theaterId(booking.getTheaterId())
                .createdAt(booking.getCreatedAt())
                .paymentId(booking.getPaymentId())
                .build();


        return BaseResponse.build(bookingDetailResponse,true);
    }

    private void markTicketUnused(List<Long> ticketIds){
      ticketIds
              .forEach(
                      ticketId -> {
                          var ticket =
                                  this.ticketService
                                          .findById(ticketId)
                                          .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND));
                          var isValidTicketUnused = !ticket.getIsUsed();
                          if (!isValidTicketUnused) throw new TicketUsedException(ErrorCode.TICKET_IS_USED);
                          ticket.markTicketUnused();
                          this.ticketService.save(ticket);
                      });
  }
  private void buildFoodsForBooking(Booking booking, List<FoodIdAndQuantityDTO> foodIdAndQuantityDTOS, AtomicReference<Float> totalPrice, Long theaterId){
      foodIdAndQuantityDTOS.stream()
              .map(
                      foodIdAndQuantityDTO -> {
                          try {
                              var priceOfFood =
                                      this.scheduleService.getPriceOfFoodById(theaterId,foodIdAndQuantityDTO.getFoodId());
                              totalPrice.updateAndGet(price -> price + priceOfFood);
                              log.info("price of food is {}",priceOfFood);
                              if (priceOfFood == null)
                                  throw new EntityNotFoundException(ErrorCode.FOOD_PRICE_NOT_FOUND);
                          } catch (Exception e) {
                              throw new EntityNotFoundException(ErrorCode.FOOD_NOT_FOUND);
                          }
                          return BookingFingerFood.builder().booking(booking).quantity(foodIdAndQuantityDTO.getQuantity()).fingerFoodId(foodIdAndQuantityDTO.getFoodId()).build();
                      })
              .forEach(bookingFingerFood -> booking.addBookingFingerFood(bookingFingerFood));
  }

  private void buildVouchersForBooking(Booking booking, List<Long> voucherIds, AtomicReference<Float> totalPrice){

              voucherIds.forEach(
                      voucherId -> {
                          var voucher =
                                  this.voucherService
                                          .findVoucherById(voucherId)
                                          .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND));
                          var isValidVoucherExpired = voucher.isExpired();
                          if (isValidVoucherExpired) throw new TicketExpireException(ErrorCode.VOUCHER_IS_EXPIRED);
                          booking.addVoucher(voucher);
                          voucher.subQuality();
                          totalPrice.updateAndGet(
                                  price -> price - voucher.getMaxPrice() * voucher.getPercent());
                      });
  }
  private void buildTicketsForBooking(Booking booking, List<Long> ticketIds, AtomicReference<Float> totalPrice){
       ticketIds
              .forEach(
                      ticketId -> {
                          var ticket =
                                  this.ticketService
                                          .findById(ticketId)
                                          .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND));
                          var isValidTicketUnused = !ticket.getIsUsed();
                          if (!isValidTicketUnused) throw new TicketUsedException(ErrorCode.TICKET_IS_USED);
                          ticket.markTicketUsed();
                          booking.addTicket(ticket);
                          totalPrice.updateAndGet(price -> price + ticket.getPrice());
                      });

  }

}
