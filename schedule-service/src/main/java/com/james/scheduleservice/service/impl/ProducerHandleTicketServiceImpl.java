package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.dto.ProducerSaveTicketDTO;
import com.james.scheduleservice.service.ProducerHandleTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerHandleTicketServiceImpl implements ProducerHandleTicketService {
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.queue.save-ticket-queue}")
  private String QUEUE_HANDLE_SAVE_TICKET;

  @Value("${rabbitmq.exchange.save-ticket-exchange}")
  private String EXCHANGE_HANDLE_SAVE_TICKET;

  @Value("${rabbitmq.routing-key.handle-save-ticket-routing-key}")
  private String ROUTING_KEY_HANDLE_SAVE_TICKET;

  @Override
  public void save(ProducerSaveTicketDTO producerSaveTicketDTO) {
    log.info("save tickets");
    this.rabbitTemplate.convertAndSend(
        EXCHANGE_HANDLE_SAVE_TICKET, ROUTING_KEY_HANDLE_SAVE_TICKET, producerSaveTicketDTO);
  }
}
