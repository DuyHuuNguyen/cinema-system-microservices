package com.james.bookingservice.facade.impl;

import com.james.bookingservice.facade.TicketFacade;
import com.james.bookingservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TickerFacadeImpl implements TicketFacade {
  private final TicketService ticketService;
}
