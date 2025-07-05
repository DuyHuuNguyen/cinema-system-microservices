package com.james.paymentservice.dto;

import com.james.paymentservice.enums.RoleEnums;
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
  private List<RoleEnums> roles;
}
