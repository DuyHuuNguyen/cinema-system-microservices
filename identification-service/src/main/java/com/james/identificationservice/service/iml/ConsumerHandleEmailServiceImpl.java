package com.james.identificationservice.service.iml;

import com.james.identificationservice.dto.EmailDTO;
import com.james.identificationservice.service.ConsumerHandleEmailService;
import com.james.identificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerHandleEmailServiceImpl implements ConsumerHandleEmailService {
  private final EmailService emailService;

  @Override
  @RabbitListener(queues = "${rabbitmq.user-mail-queue}")
  public void handleSendEmail(EmailDTO emailDTO) {
    log.info("handleSendEmail");
    this.emailService.sendEmail(emailDTO);
  }
}
