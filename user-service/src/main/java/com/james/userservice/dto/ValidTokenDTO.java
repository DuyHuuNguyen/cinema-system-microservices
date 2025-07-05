package com.james.userservice.dto;

import com.james.userservice.enums.RoleEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ValidTokenDTO {
  private UserDTO userDTO;
  private List<RoleEnum> roles;
}
