package com.james.identificationservice.service.iml;

import com.james.identificationservice.repository.UserRepository;
import com.james.identificationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceIml implements UserService {
  private final UserRepository userRepository;
}
