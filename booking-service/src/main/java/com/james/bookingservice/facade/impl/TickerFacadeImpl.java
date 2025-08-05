package com.james.bookingservice.facade.impl;

import com.james.bookingservice.entity.Ticket;
import com.james.bookingservice.enums.ErrorCode;
import com.james.bookingservice.exception.*;
import com.james.bookingservice.facade.TicketFacade;
import com.james.bookingservice.resquest.CreateTicketInternalRequest;
import com.james.bookingservice.service.ProducerHandleTicketService;
import com.james.bookingservice.service.ScheduleService;
import com.james.bookingservice.service.TicketService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    try {
      this.scheduleService.findScheduleById(request.getScheduleId());
    } catch (Exception e) {
      throw new EntityNotFoundException(ErrorCode.SCHEDULE_NOT_FOUND);
    }
    List<Ticket> tickets = new ArrayList<>();
    for (int i = 1; i <= request.getTotalSeats(); i++) {
      var ticket =
          Ticket.builder()
              .price(request.getPrice())
              .scheduleId(request.getScheduleId())
              .seatNumber(i)
              .build();
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
}
