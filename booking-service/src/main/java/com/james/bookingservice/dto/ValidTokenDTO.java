package com.james.bookingservice.dto;

import com.james.bookingservice.enums.RoleEnum;
import java.util.List;
import lombok.*;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidTokenDTO {
  private UserDTO userDTO;
  private List<RoleEnum> roles;
}
