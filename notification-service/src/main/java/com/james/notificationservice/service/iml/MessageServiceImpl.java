package com.james.notificationservice.service.iml;

import com.james.notificationservice.entity.Message;
import com.james.notificationservice.repository.MessageRepository;
import com.james.notificationservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
  private final MessageRepository messageRepository;

  @Override
  public void save(Message message) {
    this.messageRepository.save(message);
  }
  //  private final MessageService messageService;
}
