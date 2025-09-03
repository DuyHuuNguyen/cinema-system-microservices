package com.james.notificationservice.facade;

import com.james.notificationservice.resquest.TransactionSuccessNotificationRequest;

public interface MessageFacade {
  void sendNotificationSuccessTransaction(
      TransactionSuccessNotificationRequest transactionSuccessNotificationRequest);
}
