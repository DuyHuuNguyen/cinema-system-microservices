package com.james.notificationservice.service.iml;

import com.james.notificationservice.dto.MailDTO;
import com.james.notificationservice.service.ConsumerService;
import com.james.notificationservice.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    private final MailService mailService;


    @Override
    @RabbitListener(queues = "${rabbitmq-info.queue-name-user-notification}")
    public void handleSendEmail(MailDTO mailDTO) {
        log.info("handle notification {}",mailDTO);
        mailService.sendMail(mailDTO);
    }
}
