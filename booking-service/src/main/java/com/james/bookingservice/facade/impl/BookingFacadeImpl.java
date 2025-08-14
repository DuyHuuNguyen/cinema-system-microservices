package com.james.bookingservice.facade.impl;

import com.james.bookingservice.config.SecurityUserDetails;
import com.james.bookingservice.dto.FoodIdAndQuantityDTO;
import com.james.bookingservice.entity.Booking;
import com.james.bookingservice.entity.BookingFingerFood;
import com.james.bookingservice.enums.ErrorCode;
import com.james.bookingservice.enums.PaymentStatus;
import com.james.bookingservice.enums.PaymentType;
import com.james.bookingservice.exception.EntityNotFoundException;
import com.james.bookingservice.exception.TicketExpireException;
import com.james.bookingservice.exception.TicketUsedException;
import com.james.bookingservice.facade.BookingFacade;
import com.james.bookingservice.resquest.CreateBookingTicketRequest;
import com.james.bookingservice.resquest.CreatePaymentRequest;
import com.james.bookingservice.service.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
