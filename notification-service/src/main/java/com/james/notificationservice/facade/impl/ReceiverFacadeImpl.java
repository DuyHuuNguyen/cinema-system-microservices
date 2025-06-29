package com.james.notificationservice.facade.impl;

import com.james.notificationservice.facade.ReceiverFacade;
import com.james.notificationservice.service.ReceiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiverFacadeImpl implements ReceiverFacade {
  private final ReceiverService receiverService;
}
