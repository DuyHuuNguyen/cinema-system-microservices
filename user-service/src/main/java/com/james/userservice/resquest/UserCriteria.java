package com.james.userservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCriteria extends BaseCriteria {
  private String firstname;
  private String lastname;
  private String email;
  private Long dateOfBirth;
}
