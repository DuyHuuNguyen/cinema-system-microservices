package com.james.notificationservice.service.iml;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl {
  private final RabbitTemplate rabbitTemplate;
}
