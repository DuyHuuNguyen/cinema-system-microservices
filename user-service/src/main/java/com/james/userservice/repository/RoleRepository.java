package com.james.userservice.repository;

import com.james.userservice.entity.Role;
import com.james.userservice.enums.RoleEnum;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findRoleByRoleName(RoleEnum roleEnum);
}
