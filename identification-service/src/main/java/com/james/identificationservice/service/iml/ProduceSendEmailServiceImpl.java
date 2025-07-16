package com.james.identificationservice.service.iml;

import com.james.identificationservice.dto.EmailDTO;
import com.james.identificationservice.service.ProducerSendEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProduceSendEmailServiceImpl implements ProducerSendEmailService {
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.exchangeName}")
  private String EXCHANGE_NAME;

  @Value("${rabbitmq.user-mail-routing-key}")
  private String ROUTING_KEY;

  @Override
  public void sendEmail(EmailDTO emailDTO) {
    this.rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, emailDTO);
  }
}
