package com.james.userservice.service.impl;

import com.james.userservice.repository.HobbyRepository;
import com.james.userservice.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HobbyServiceImpl implements HobbyService {
  private final HobbyRepository hobbyRepository;
}
