package com.james.userservice.service.impl;

import com.james.userservice.entity.User;
import com.james.userservice.repository.UserRepository;
import com.james.userservice.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

  @Override
  public Optional<User> findUserById(Long id) {
    return this.userRepository.findById(id);
  }

  @Override
  public List<User> findUserByIds(List<Long> ids) {
    return this.userRepository.findUserByIds(ids);
  }

  @Override
  public Page<User> findAll(Specification<User> specification, Pageable pageable) {
    return this.userRepository.findAll(specification, pageable);
  }
}
