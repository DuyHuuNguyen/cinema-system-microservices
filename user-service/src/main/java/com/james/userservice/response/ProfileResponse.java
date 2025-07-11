package com.james.userservice.response;

import com.james.userservice.dto.LocationDTO;
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
public class ProfileResponse {
  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private String avatarKey;
  private Long dateOfBirth;
  private Boolean isLoyalCustomer = false;
  private LocationDTO locationDTO;
  private List<HobbyEnum> hobbies;
}
