package com.james.notificationservice.service;

import com.james.notificationservice.dto.MailDTO;

public interface ConsumerService {
  void handleSendEmail(MailDTO mailDTO);
}
