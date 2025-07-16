package com.james.identificationservice.service;

import com.james.identificationservice.entity.User;
import java.util.Optional;

public interface UserService {
  Optional<User> findByEmail(String email);

  void save(User user);
}
