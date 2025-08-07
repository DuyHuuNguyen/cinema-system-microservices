package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.dto.ProducerSaveTicketDTO;
import com.james.scheduleservice.resquest.CreateTicketInternalRequest;
import com.james.scheduleservice.service.BookingService;
import com.james.scheduleservice.service.ConsumerHandleTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerHandleTicketServiceImpl implements ConsumerHandleTicketService {
  private final BookingService bookingService;

  @Override
  @RabbitListener(queues = "${rabbitmq.queue.save-ticket-queue}")
  public void saveTicket(ProducerSaveTicketDTO producerSaveTicketDTO) {
    var request =
        CreateTicketInternalRequest.builder()
            .price(producerSaveTicketDTO.getPrice())
            .totalSeats(producerSaveTicketDTO.getTotalSeats())
            .scheduleCode(producerSaveTicketDTO.getScheduleCode())
            .build();
    try {
      bookingService.createTicketInternal(request);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
