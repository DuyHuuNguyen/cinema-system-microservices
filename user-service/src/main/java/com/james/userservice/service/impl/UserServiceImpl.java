package com.james.userservice.service.impl;

import com.james.userservice.repository.UserRepository;
import com.james.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
}
