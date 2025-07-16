package com.james.identificationservice.service.iml;

import com.james.identificationservice.entity.User;
import com.james.identificationservice.repository.UserRepository;
import com.james.identificationservice.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceIml implements UserService {
  private final UserRepository userRepository;

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  @Override
  public void save(User user) {
    this.userRepository.save(user);
  }
}
