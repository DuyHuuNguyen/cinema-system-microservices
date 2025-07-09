package com.james.userservice.resquest;

import com.james.userservice.enums.RoleEnum;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChangeRoleRequest {
  @Hidden private Long userId;
  private Set<RoleEnum> roles;

  public void setUserId(Long id) {
    this.userId = id;
  }
}
