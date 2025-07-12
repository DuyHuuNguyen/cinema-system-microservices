package com.james.userservice.facade.impl;

import com.james.userservice.config.SecurityUserDetails;
import com.james.userservice.dto.*;
import com.james.userservice.entity.Location;
import com.james.userservice.entity.Role;
import com.james.userservice.entity.User;
import com.james.userservice.enums.ErrorCode;
import com.james.userservice.enums.RoleEnum;
import com.james.userservice.exception.EmailIsUsedException;
import com.james.userservice.exception.EntityNotFoundException;
import com.james.userservice.facade.UserFacade;
import com.james.userservice.mapper.UserMapper;
import com.james.userservice.response.BaseResponse;
import com.james.userservice.response.PaginationResponse;
import com.james.userservice.response.ProfileResponse;
import com.james.userservice.response.UserResponse;
import com.james.userservice.resquest.*;
import com.james.userservice.service.*;
import com.james.userservice.specification.UserSpecification;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
  private final UserService userService;
  private final HobbyService hobbyService;
  private final PasswordEncoder passwordEncoder;
  private final RoleService roleService;
  private final UserMapper userMapper;
  private final ScheduleService scheduleService;
  private final NotificationService notificationService;

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
    this.notificationService.sendEmailInviteWatchingMovie(inviteWatchingMovieDTO);
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

    var theaterProfileResponse = this.scheduleService.findTheaterById(request.getTheaterId());

    var isNotFoundTheater = theaterProfileResponse != null;
    if (isNotFoundTheater) throw new EntityNotFoundException(ErrorCode.THEATER_NOT_FOUND);

    JobApplicationDTO jobApplicationDTO =
        JobApplicationDTO.builder()
            .theaterId(theaterProfileResponse.getId())
            .theaterName(theaterProfileResponse.getTheaterName())
            .userId(user.getId())
            .build();
    this.notificationService.sendEmailApplyJob(jobApplicationDTO);
  }

  @Override
  @Transactional
  public void changeRole(ChangeRoleRequest request) {
    log.info("role :{}", request.getRoles());
    var user =
        this.userService
            .findUserById(request.getUserId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    var roles = new ArrayList<Role>();
    for (var roleEnum : request.getRoles()) {
      var role =
          this.roleService
              .findRoleByEnum(roleEnum)
              .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ROLE_NOT_FOUND));
      roles.add(role);
    }
    user.changeRole(roles);
    this.userService.save(user);
  }

  @Override
  public BaseResponse<PaginationResponse<UserResponse>> getByFilter(UserCriteria userCriteria) {
    var pageable = PageRequest.of(userCriteria.getCurrentPage(), userCriteria.getPageSize());
    Specification<User> userSpecification =
        UserSpecification.hasEmail(userCriteria.getEmail())
            .and(UserSpecification.hasFirstname(userCriteria.getFirstname()))
            .and(UserSpecification.hasLastname(userCriteria.getLastname()))
            .and(UserSpecification.hasDateOfBirth(userCriteria.getDateOfBirth()));
    var userPage = this.userService.findAll(userSpecification, pageable);

    return BaseResponse.build(
        PaginationResponse.<UserResponse>builder()
            .data(userPage.get().map(user -> this.userMapper.toUserResponse(user)).toList())
            .currentPage(userCriteria.getCurrentPage())
            .totalElements(userPage.getNumberOfElements())
            .totalPages(userPage.getTotalPages())
            .build(),
        true);
  }
}
