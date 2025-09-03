package com.james.notificationservice.service;

import com.james.notificationservice.dto.MailDTO;

public interface MailService {
  void sendMail(MailDTO mailDTO);
}
