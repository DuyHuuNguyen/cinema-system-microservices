package com.james.userservice.dto;

import lombok.*;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private Long id;
  private String email;
  private String password;
}
