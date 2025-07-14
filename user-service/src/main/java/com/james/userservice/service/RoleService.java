package com.james.userservice.service;

import com.james.userservice.entity.Role;
import com.james.userservice.enums.RoleEnum;
import java.util.Optional;

public interface RoleService {
  Optional<Role> findRoleByEnum(RoleEnum roleEnum);
}
