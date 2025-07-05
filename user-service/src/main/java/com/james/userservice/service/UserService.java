package com.james.userservice.service;

import com.james.userservice.entity.User;
import java.util.Optional;

public interface UserService {
  void save(User user);

  boolean verify(String email);

  Optional<User> findUserById(Long id);
}
