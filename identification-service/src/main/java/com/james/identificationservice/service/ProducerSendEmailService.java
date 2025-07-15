package com.james.identificationservice.service;

import com.james.identificationservice.dto.EmailDTO;

public interface ProducerSendEmailService {
  void sendEmail(EmailDTO emailDTO);
}
