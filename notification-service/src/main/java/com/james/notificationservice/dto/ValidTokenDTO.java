package com.james.notificationservice.dto;

import com.james.notificationservice.enums.RoleEnums;
import java.util.List;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ValidTokenDTO {
  private UserDTO userDTO;
  private List<RoleEnums> roles;
}
