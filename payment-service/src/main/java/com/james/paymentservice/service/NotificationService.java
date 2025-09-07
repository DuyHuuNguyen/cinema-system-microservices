package com.james.paymentservice.service;

import com.james.paymentservice.resquest.TransactionFailNotificationRequest;
import com.james.paymentservice.resquest.TransactionSuccessNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification-service")
public interface NotificationService {
  @PostMapping(value = "/api/v1/notifications/internal", headers = "secret-key=transaction-success")
  void sendNotification(TransactionSuccessNotificationRequest transactionNotificationRequest);

  void sendNotification(TransactionFailNotificationRequest transactionNotificationRequest);
}
