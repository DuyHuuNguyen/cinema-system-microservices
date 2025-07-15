package com.james.identificationservice.service;

import com.james.identificationservice.dto.EmailDTO;

public interface EmailService {
  void sendEmail(EmailDTO emailDTO);
}
