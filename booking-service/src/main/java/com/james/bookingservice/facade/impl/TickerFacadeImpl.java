package com.james.bookingservice.facade.impl;

import com.james.bookingservice.config.SecurityUserDetails;
import com.james.bookingservice.entity.Ticket;
import com.james.bookingservice.enums.ErrorCode;
import com.james.bookingservice.exception.*;
import com.james.bookingservice.facade.TicketFacade;
import com.james.bookingservice.resquest.ChangePriceTicketsRequest;
import com.james.bookingservice.resquest.CreateTicketInternalRequest;
import com.james.bookingservice.resquest.ValidAdminTheaterRequest;
import com.james.bookingservice.resquest.ValidScheduleOfTheaterRequest;
import com.james.bookingservice.service.ProducerHandleTicketService;
import com.james.bookingservice.service.ScheduleService;
import com.james.bookingservice.service.TicketService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TickerFacadeImpl implements TicketFacade {
  private final TicketService ticketService;
  private final ScheduleService scheduleService;
  private final ProducerHandleTicketService producerHandleTicketService;

  @Override
  @Transactional
  public void createTicketInternal(CreateTicketInternalRequest request) {
    Long scheduleId;
    try {
      scheduleId = this.scheduleService.findScheduleByCode(request.getScheduleCode());
    } catch (Exception e) {
      throw new EntityNotFoundException(ErrorCode.SCHEDULE_NOT_FOUND);
    }
    List<Ticket> tickets = new ArrayList<>();
    for (int i = 1; i <= request.getTotalSeats(); i++) {
      var ticket =
          Ticket.builder().price(request.getPrice()).scheduleId(scheduleId).seatNumber(i).build();
      log.info("Creating ticket {}", ticket);
      tickets.add(ticket);
    }
    this.producerHandleTicketService.save(tickets);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    var ticket =
        this.ticketService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND));
    this.ticketService.delete(ticket);
  }

  @Override
  @Transactional
  public void changeTicketsPrice(ChangePriceTicketsRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    try {
      var validAdminTheaterRequest =
          ValidAdminTheaterRequest.builder()
              .adminId(principal.getId())
              .theaterId(request.getTheaterId())
              .build();

      var isValidAdmin = this.scheduleService.validAdminTheater(validAdminTheaterRequest);
      if (!isValidAdmin) throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED);

      var validScheduleOfTheaterRequest =
          ValidScheduleOfTheaterRequest.builder()
              .scheduleId(request.getScheduleId())
              .theaterId(request.getTheaterId())
              .build();

      // bug logic verify input

      var isValidScheduleOfTheater =
          this.scheduleService.validScheduleOfTheater(validScheduleOfTheaterRequest);
      if (!isValidScheduleOfTheater)
        throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED);

    } catch (Exception e) {
      throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED);
    }

    var ticketIds = request.getTicketIds();
    var isSomeTicketsChanged = ticketIds == null || ticketIds.isEmpty();

    List<Ticket> tickets;
    if (isSomeTicketsChanged) {
      tickets = new ArrayList<>();
      ticketIds.forEach(
          ticketId -> {
            var ticket =
                this.ticketService
                    .findById(ticketId)
                    .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND));
            tickets.add(ticket);
          });
    } else {
      tickets = this.ticketService.findTicketsByScheduleId(request.getScheduleId());
      var isTicketsNotFound = tickets == null || tickets.isEmpty();
      if (isTicketsNotFound) throw new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND);
    }

    for (var ticket : tickets) {
      ticket.changePrice(request.getPrice());
    }
    this.producerHandleTicketService.save(tickets);
  }
}
