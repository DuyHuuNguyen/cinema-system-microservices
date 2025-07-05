package com.james.userservice.service;

import com.james.userservice.entity.User;

public interface UserService {
  void save(User user);

  boolean verify(String email);
}
