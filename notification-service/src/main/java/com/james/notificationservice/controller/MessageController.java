package com.james.notificationservice.controller;

import com.james.notificationservice.facade.MessageFacade;
import com.james.notificationservice.resquest.TransactionSuccessNotificationRequest;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
  private final MessageFacade messageFacade;

  @Hidden
  @GetMapping("/internal/notification")
  public void sendNotification(
      @RequestBody TransactionSuccessNotificationRequest transactionSuccessNotificationRequest) {
    this.messageFacade.sendNotificationSuccessTransaction(transactionSuccessNotificationRequest);
  }
}
