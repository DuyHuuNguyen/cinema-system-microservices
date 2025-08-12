package com.james.bookingservice.facade.impl;

import com.james.bookingservice.config.SecurityUserDetails;
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
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {
  private final BookingService bookingService;
  private final TicketService ticketService;
  private final VoucherService voucherService;
  private final TheaterService theaterService;
  private final PaymentService paymentService;

  @Override
  public void createBooking(CreateBookingTicketRequest request) {
    AtomicReference<Float> totalPrice = new AtomicReference<>(0F);

    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    var booking =
        Booking.builder()
            .paymentId(request.getPaymentId())
            .userId(principal.getId())
            .theaterId(request.getTheaterId())
            .build();

    request
        .getTicketIds()
        .forEach(
            ticketId -> {
              var ticket =
                  this.ticketService
                      .findById(ticketId)
                      .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND));
              var isValidTicketUnused = !ticket.getIsUsed();
              if (!isValidTicketUnused) throw new TicketUsedException(ErrorCode.TICKET_NOT_FOUND);
              booking.addTicket(ticket);
              totalPrice.updateAndGet(price -> price + ticket.getPrice());
            });

    request
        .getVoucherId()
        .forEach(
            voucherId -> {
              var voucher =
                  this.voucherService
                      .findVoucherById(voucherId)
                      .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND));
              var isValidVoucherExpired = voucher.isExpired();
              if (isValidVoucherExpired)
                throw new TicketExpireException(ErrorCode.TICKET_NOT_FOUND);
              booking.addVoucher(voucher);
              totalPrice.updateAndGet(
                  price -> price - voucher.getMaxPrice() * voucher.getPercent());
            });

    request.getFoodIds().stream()
        .map(
            foodId -> {
              try {
                var priceOfFood =
                    this.theaterService.getPriceOfFoodById(foodId, request.getTheaterId());
                totalPrice.updateAndGet(price -> price + priceOfFood);
                if (priceOfFood == null)
                  throw new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND);
              } catch (Exception e) {
                throw new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND);
              }
              return BookingFingerFood.builder().booking(booking).fingerFoodId(foodId).build();
            })
        .forEach(bookingFingerFood -> booking.addBookingFingerFood(bookingFingerFood));
    var newBooking = this.bookingService.save(booking);

    var isPaymentTypeCashOnDelivery = request.getPaymentType().isCashOnDelivery();

    if (isPaymentTypeCashOnDelivery) {
      var paymentRequest =
          CreatePaymentRequest.builder()
              .paymentStatus(PaymentStatus.FAILURE)
              .paymentType(PaymentType.COD)
              .price(totalPrice.get())
              .build();
      try {
        paymentService.createPayment(paymentRequest);
      } catch (Exception e) {
        this.bookingService.delete(newBooking);
        log.info("rollback : {}", e.getMessage());
      }
    } else {
      Boolean isValidPaymentCompleted = null;
      try {
        isValidPaymentCompleted =
            this.paymentService.verifyPayment(
                request.getPaymentId(), request.getTheaterId(), newBooking.getId());
      } catch (Exception e) {
        this.bookingService.delete(newBooking);
        log.info("rollback  : {}", e.getMessage());
      }
      if (isValidPaymentCompleted == null || !isValidPaymentCompleted)
        this.bookingService.delete(newBooking);
    }
    log.info(
        " booking true : id {}, code {}, price {} ",
        newBooking.getId(),
        booking.getBookingCode(),
        totalPrice.get());
  }
}
