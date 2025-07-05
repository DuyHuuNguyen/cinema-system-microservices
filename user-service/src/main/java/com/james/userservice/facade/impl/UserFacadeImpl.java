package com.james.userservice.facade.impl;

import com.james.userservice.entity.Location;
import com.james.userservice.entity.User;
import com.james.userservice.enums.ErrorCode;
import com.james.userservice.enums.RoleEnum;
import com.james.userservice.exception.EmailIsUsedException;
import com.james.userservice.exception.EntityNotFoundException;
import com.james.userservice.facade.UserFacade;
import com.james.userservice.resquest.UpsertUserRequest;
import com.james.userservice.service.HobbyService;
import com.james.userservice.service.LocationService;
import com.james.userservice.service.RoleService;
import com.james.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
  private final UserService userService;
  private final LocationService locationService;
  private final HobbyService hobbyService;
  private final PasswordEncoder passwordEncoder;
  private final RoleService roleService;

  @Override
  @Transactional
  public void signUp(UpsertUserRequest upsertUserRequest) {
    var isEmailAvailable = this.userService.verify(upsertUserRequest.getEmail());
    if (!isEmailAvailable) throw new EmailIsUsedException(ErrorCode.EMAIL_IS_USED);

    var passwordEncoded = this.passwordEncoder.encode(upsertUserRequest.getPassword());

    var user =
        User.builder()
            .email(upsertUserRequest.getEmail())
            .password(passwordEncoded)
            .firstname(upsertUserRequest.getFirstName())
            .lastname(upsertUserRequest.getLastName())
            .avatarKey(upsertUserRequest.getAvatarUrl())
            .dateOfBirth(upsertUserRequest.getDateOfBirth())
            .build();

    var role =
        this.roleService
            .findRoleByEnum(RoleEnum.USER)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ROLE_NOT_FOUND));
    user.addRole(role);

    var isHasLocation =
        upsertUserRequest.getLatitude() != null && upsertUserRequest.getLongitude() != null;

    if (isHasLocation) {
      var location =
          Location.builder()
              .longitude(upsertUserRequest.getLongitude())
              .latitude(upsertUserRequest.getLatitude())
              .build();
      user.addLocation(location);
    }

    var isHasHobby =
        upsertUserRequest.getHobbies() != null && !upsertUserRequest.getHobbies().isEmpty();

    if (isHasHobby) {
      var hobbies = this.hobbyService.findHobbiesByHobbyEnums(upsertUserRequest.getHobbies());
      if (!hobbies.isEmpty()) {
        for (var hobby : hobbies) {
          user.addHobby(hobby);
        }
      }
    }

    this.userService.save(user);
  }
}
