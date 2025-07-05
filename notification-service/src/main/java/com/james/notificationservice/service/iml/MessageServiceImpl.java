package com.james.notificationservice.service.iml;

import com.james.notificationservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
  private final MessageService messageService;
}
