package com.james.userservice.service.impl;

import com.james.userservice.entity.Role;
import com.james.userservice.enums.RoleEnum;
import com.james.userservice.repository.RoleRepository;
import com.james.userservice.service.RoleService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  @Override
  public Optional<Role> findRoleByEnum(RoleEnum roleEnum) {
    return roleRepository.findRoleByRoleName(roleEnum);
  }
}
