package com.james.notificationservice.service.iml;

import com.james.notificationservice.repository.ReceiverRepository;
import com.james.notificationservice.service.ReceiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiverServiceImpl implements ReceiverService {
  private final ReceiverRepository receiverRepository;
}
