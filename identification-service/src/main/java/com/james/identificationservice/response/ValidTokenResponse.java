package com.james.identificationservice.response;

import com.james.identificationservice.dto.UserDTO;
import com.james.identificationservice.enums.RoleEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ValidTokenResponse {
  private UserDTO userDTO;
  private List<RoleEnum> roles;
}
