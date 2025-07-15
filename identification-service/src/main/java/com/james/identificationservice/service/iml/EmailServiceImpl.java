package com.james.identificationservice.service.iml;

import com.james.identificationservice.dto.EmailDTO;
import com.james.identificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
  private final JavaMailSender javaMailSender;

  @Override
  public void sendEmail(EmailDTO emailDTO) {
    var mail = new SimpleMailMessage();
    mail.setTo(emailDTO.getToEmail());
    mail.setSubject(emailDTO.getSubject());
    mail.setText(emailDTO.getBody());
    javaMailSender.send(mail);
  }
}
