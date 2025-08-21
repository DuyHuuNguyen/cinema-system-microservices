package com.james.bookingservice.service.impl;

import com.james.bookingservice.entity.Ticket;
import com.james.bookingservice.service.ConsumerHandleTicketService;
import com.james.bookingservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerHandleTicketServiceImpl implements ConsumerHandleTicketService {
  private final TicketService ticketService;

  @Override
  @Transactional
  @RabbitListener(queues = {"${rabbitmq.variable.handle-ticket-queue}"})
  public void save(Ticket ticket) {
    log.info("ticket info {}", ticket.toString());
    this.ticketService.save(ticket);
  }
}
