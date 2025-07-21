package com.james.movieservice.dto;

import com.james.movieservice.enums.RoleEnum;
import java.util.List;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ValidTokenDTO {
  private UserDTO userDTO;
  private List<RoleEnum> roles;
}
