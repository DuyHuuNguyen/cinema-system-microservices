package com.james.notificationservice.facade.impl;

import com.james.notificationservice.facade.MessageFacade;
import com.james.notificationservice.service.MessageAssetService;
import com.james.notificationservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageFacadeImpl implements MessageFacade {
  private final MessageService messageService;
  private final MessageAssetService messageAssetService;
}
