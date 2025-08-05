package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.dto.ProducerSaveTicketDTO;
import com.james.scheduleservice.resquest.CreateTicketInternalRequest;
import com.james.scheduleservice.service.BookingService;
import com.james.scheduleservice.service.ConsumerHandleTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerHandleTicketServiceImpl implements ConsumerHandleTicketService {
  private final BookingService bookingService;

  @Override
  @RabbitListener(queues = "${rabbitmq.variable.save-ticket-queue}")
  public void saveTicket(ProducerSaveTicketDTO producerSaveTicketDTO) {
    var request =
        CreateTicketInternalRequest.builder()
            .price(producerSaveTicketDTO.getPrice())
            .totalSeats(producerSaveTicketDTO.getTotalSeats())
            .scheduleId(producerSaveTicketDTO.getScheduleId())
            .build();
    bookingService.createTicketInternal(request);
  }
}
