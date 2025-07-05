package com.james.userservice.service.impl;

import com.james.userservice.entity.User;
import com.james.userservice.repository.UserRepository;
import com.james.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public void save(User user) {
    this.userRepository.save(user);
  }

  @Override
  public boolean verify(String email) {
    return this.userRepository.verify(email);
  }
}
