package com.james.userservice.facade.impl;

import com.james.userservice.config.SecurityUserDetails;
import com.james.userservice.dto.*;
import com.james.userservice.entity.Location;
import com.james.userservice.entity.User;
import com.james.userservice.enums.ErrorCode;
import com.james.userservice.enums.RoleEnum;
import com.james.userservice.exception.EmailIsUsedException;
import com.james.userservice.exception.EntityNotFoundException;
import com.james.userservice.facade.UserFacade;
import com.james.userservice.mapper.UserMapper;
import com.james.userservice.response.BaseResponse;
import com.james.userservice.response.ProfileResponse;
import com.james.userservice.resquest.ChangeLocationRequest;
import com.james.userservice.resquest.InviteWatchingMovieRequest;
import com.james.userservice.resquest.SignUpUserRequest;
import com.james.userservice.resquest.UpdateUserRequest;
import com.james.userservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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
  private final UserMapper userMapper;
  private final MovieService movieService;
  private final ScheduleService scheduleService;

  @Override
  @Transactional
  public void signUp(SignUpUserRequest upsertUserRequest) {
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

  @Override
  @Transactional
  public void updateProfile(UpdateUserRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    var user =
        this.userService
            .findUserById(principal.id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

    var newHobbies = this.hobbyService.findHobbiesByHobbyEnums(request.getHobbies());
    if (!newHobbies.isEmpty()) {
      user.changeHobbies(newHobbies);
    }

    var profileDTO =
        ProfileDTO.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .avatarUrl(request.getAvatarUrl())
            .dateOfBirth(request.getDateOfBirth())
            .build();

    user.changeProfile(profileDTO);

    var newLocation =
        Location.builder()
            .longitude(request.getLongitude())
            .latitude(request.getLatitude())
            .build();
    user.addLocation(newLocation);

    this.userService.save(user);
  }

  @Override
  public BaseResponse<ProfileResponse> getProfile() {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    var user =
        this.userService
            .findUserById(principal.id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    return BaseResponse.build(this.userMapper.toProfile(user), true);
  }

  @Override
  public void inviteWatchingMovie(InviteWatchingMovieRequest request) {
    var users = this.userService.findUserByIds(request.getIds());

    var emailDTOS =
        users.stream()
            .map(user -> EmailDTO.builder().email(user.getEmail()).id(user.getId()).build())
            .toList();

    var scheduleDTO = this.scheduleService.findScheduleById(request.getScheduleId());

    var inviteWatchingMovieDTO =
        InviteWatchingMovieDTO.builder().emailDTOS(emailDTOS).scheduleDTO(scheduleDTO).build();
  }

  @Override
  @Transactional
  public void changeLocation(ChangeLocationRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    var user =
        this.userService
            .findUserById(principal.getId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

    var newlocation =
        Location.builder()
            .longitude(request.getLongitude())
            .latitude(request.getLatitude())
            .build();

    user.addLocation(newlocation);
    userService.save(user);
  }

  @Override
  public void jobApplication(JobApplicationRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    var user =
        this.userService
            .findUserById(principal.getId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

    var theaterDTO = this.scheduleService.findTheaterById(request.getTheaterId());

    var isNotFoundTheater = theaterDTO != null;
    if (isNotFoundTheater) throw new EntityNotFoundException(ErrorCode.THEATER_NOT_FOUND);

    var jobApplicationDTO =
        JobApplicationDTO.builder().theaterDTO(theaterDTO).userId(user.getId()).build();
  }
}
