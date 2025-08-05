package com.james.bookingservice.service.impl;

import com.james.bookingservice.entity.Ticket;
import com.james.bookingservice.service.ConsumerHandleTicketService;
import com.james.bookingservice.service.TicketService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerHandleTicketServiceImpl implements ConsumerHandleTicketService {
  private final TicketService ticketService;

  @Override
  @RabbitListener(queues = {"${rabbitmq.variable.handle-ticket-queue}"})
  public void save(List<Ticket> tickets) {
    tickets.forEach(this.ticketService::save);
  }
}
