package com.james.notificationservice.service.iml;

import com.james.notificationservice.dto.MailDTO;
import com.james.notificationservice.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq-info.routing-key-user-notification}")
  private String ROUTING_KEY_USER_NOTIFICATION;

  @Override
  public void sendNotification(MailDTO mailDTO) {
    log.info("sending notification {}", mailDTO);
    this.rabbitTemplate.convertAndSend(this.ROUTING_KEY_USER_NOTIFICATION, mailDTO);
  }
}
