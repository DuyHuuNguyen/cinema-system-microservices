package com.james.userservice.service.impl;

import com.james.userservice.repository.RoleRepository;
import com.james.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;
}
