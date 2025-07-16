package com.james.identificationservice.service;

import com.james.identificationservice.dto.EmailDTO;

public interface ConsumerHandleEmailService {
  void handleSendEmail(EmailDTO emailDTO);
}
