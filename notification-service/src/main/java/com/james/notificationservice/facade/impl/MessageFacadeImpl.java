package com.james.notificationservice.facade.impl;

import com.james.notificationservice.entity.Message;
import com.james.notificationservice.facade.MessageFacade;
import com.james.notificationservice.resquest.TransactionSuccessNotificationRequest;
import com.james.notificationservice.service.MessageService;
import com.james.notificationservice.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageFacadeImpl implements MessageFacade {
  private final MessageService messageService;
  private final ProducerService producerService;

  @Override
  public void sendNotificationSuccessTransaction(
      TransactionSuccessNotificationRequest transactionSuccessNotificationRequest) {
    var message = Message.builder().build();
  }
}
