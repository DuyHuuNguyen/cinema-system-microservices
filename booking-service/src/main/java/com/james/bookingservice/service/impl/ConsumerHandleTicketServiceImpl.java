package com.james.bookingservice.service.impl;

import com.james.bookingservice.entity.Ticket;
import com.james.bookingservice.service.ConsumerHandleTicketService;
import com.james.bookingservice.service.TicketService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerHandleTicketServiceImpl implements ConsumerHandleTicketService {
  private final TicketService ticketService;

  @Override
  @RabbitListener(queues = {"${rabbitmq.variable.handle-ticket-queue}"})
  public void save(List<Ticket> tickets) {
    try {
      tickets.forEach(this.ticketService::save);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
