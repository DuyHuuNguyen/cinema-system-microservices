package com.james.notificationservice.service.iml;

import com.james.notificationservice.dto.MailDTO;
import com.james.notificationservice.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(MailDTO mailDTO) {
        var mail = new SimpleMailMessage();
        mail.setTo(mailDTO.getTo());
        mail.setSubject(mailDTO.getSubject());
        mail.setText(mailDTO.getBody());
        javaMailSender.send(mail);
    }
}
