package com.james.userservice.dto;

import com.james.userservice.enums.RoleEnum;
import java.util.List;
import lombok.*;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ValidTokenDTO {
  private UserDTO userDTO;
  private List<RoleEnum> roles;
}
