package com.james.userservice.resquest;

import com.james.userservice.enums.HobbyEnum;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertUserRequest {
  @Hidden private Long id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String avatarUrl;
  private Long dateOfBirth;

  private Long longitude;
  private Long latitude;

  private List<HobbyEnum> hobbies;

  public void setUserId(Long userId) {
    this.id = userId;
  }
}
