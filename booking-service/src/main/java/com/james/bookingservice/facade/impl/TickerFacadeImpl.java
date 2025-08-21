package com.james.bookingservice.facade.impl;

import com.james.bookingservice.config.SecurityUserDetails;
import com.james.bookingservice.entity.Ticket;
import com.james.bookingservice.enums.ActiveEnum;
import com.james.bookingservice.enums.ErrorCode;
import com.james.bookingservice.exception.*;
import com.james.bookingservice.facade.TicketFacade;
import com.james.bookingservice.resquest.*;
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
      ticket.changeActive(ActiveEnum.HIDDEN_DEFAULT.getIsActive());
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
    this.verifyAdminTheater(request.getTheaterId(), request.getScheduleId());
    var ticketIds = request.getTicketIds();
    var isSomeTicketsChanged = ticketIds != null && !ticketIds.isEmpty();

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

  @Override
  public void releaseTickets(ReleaseTicketsRequest request) {
    this.verifyAdminTheater(request.getTheaterId(), request.getScheduleId());
    var ticketIds = request.getTicketIds();
    var isSomeTicketsChanged = ticketIds != null && !ticketIds.isEmpty();

    List<Ticket> tickets;
    if (isSomeTicketsChanged) {
      tickets = new ArrayList<>();
      ticketIds.forEach(
          ticketId -> {
            var ticket =
                this.ticketService
                    .findById(ticketId)
                    .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND));
            ticket.release();
            tickets.add(ticket);
          });
    } else {
      tickets = this.ticketService.findTicketsByScheduleId(request.getScheduleId());
      var isTicketsNotFound = tickets == null || tickets.isEmpty();
      if (isTicketsNotFound) throw new EntityNotFoundException(ErrorCode.TICKET_NOT_FOUND);
      tickets.forEach(Ticket::release);
    }

    this.producerHandleTicketService.save(tickets);
  }

  private void verifyAdminTheater(Long theaterId, Long scheduleId) {
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

      var validScheduleOfTheaterRequest =
          ValidScheduleOfTheaterRequest.builder()
              .scheduleId(scheduleId)
              .theaterId(theaterId)
              .build();

      var isValidScheduleOfTheater =
          this.scheduleService.validScheduleOfTheater(validScheduleOfTheaterRequest);
      if (!isValidScheduleOfTheater)
        throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED);

    } catch (Exception e) {
      throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED);
    }
  }
}
