package com.james.userservice.service;

import com.james.userservice.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {
  void save(User user);

  boolean verify(String email);

  Optional<User> findUserById(Long id);

  List<User> findUserByIds(List<Long> ids);

  Page<User> findAll(Specification<User> specification, Pageable pageable);
}
