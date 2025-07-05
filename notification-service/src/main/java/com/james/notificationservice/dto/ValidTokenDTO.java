package com.james.notificationservice.dto;

import com.james.notificationservice.enums.RoleEnums;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ValidTokenDTO {
  private UserDTO userDTO;
  private List<RoleEnums> roles;
}
