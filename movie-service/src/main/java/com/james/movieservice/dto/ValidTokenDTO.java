package com.james.movieservice.dto;

import com.james.movieservice.enums.RoleEnum;
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
  private List<RoleEnum> roles;
}
