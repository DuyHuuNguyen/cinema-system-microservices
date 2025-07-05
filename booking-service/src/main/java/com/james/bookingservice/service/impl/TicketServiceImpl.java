package com.james.bookingservice.service.impl;

import com.james.bookingservice.repository.TicketRepository;
import com.james.bookingservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
  private final TicketRepository ticketRepository;
}
