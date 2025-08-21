package com.james.bookingservice.service.impl;

import com.james.bookingservice.entity.Ticket;
import com.james.bookingservice.service.ProducerHandleTicketService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerHandleTickerServiceImpl implements ProducerHandleTicketService {
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.variable.handle-ticket-exchange}")
  private String HANDLE_TICKET_EXCHANGE;

  @Value("${rabbitmq.variable.handle-ticket-routing-key}")
  private String HANDLE_TICKET_ROUTING_KEY;

  @Override
  public void save(List<Ticket> tickets) {
    log.info("save tickets");
    for (Ticket ticket : tickets) {
      this.rabbitTemplate.convertAndSend(HANDLE_TICKET_EXCHANGE, HANDLE_TICKET_ROUTING_KEY, ticket);
    }
    //    this.rabbitTemplate.convertAndSend(HANDLE_TICKET_EXCHANGE, HANDLE_TICKET_ROUTING_KEY,
    // tickets);
  }

  //  @PostConstruct
  //  void run(){
  //    this.rabbitTemplate.convertAndSend(HANDLE_TICKET_EXCHANGE, HANDLE_TICKET_ROUTING_KEY,
  // List.of(Ticket.builder().build()));
  //  }
}
