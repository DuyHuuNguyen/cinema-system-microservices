package com.james.notificationservice.service;

import com.james.notificationservice.dto.MailDTO;

public interface ProducerService {
    void sendNotification(MailDTO mailDTO);
}
