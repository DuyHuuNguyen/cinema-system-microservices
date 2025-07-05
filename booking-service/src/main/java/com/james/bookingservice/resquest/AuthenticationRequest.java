package com.james.bookingservice.resquest;

import com.james.bookingservice.dto.UserDTO;
import com.james.bookingservice.enums.RoleEnum;
import java.util.List;

import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
  private UserDTO userDTO;
  private List<RoleEnum> roles;
}
