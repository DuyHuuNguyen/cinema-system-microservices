package com.james.userservice.resquest;

import com.james.userservice.enums.HobbyEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateUserRequest {

  private String firstName;
  private String lastName;
  private String avatarUrl;
  private Long dateOfBirth;
  private Long longitude;
  private Long latitude;
  private List<HobbyEnum> hobbies;
}
