package com.james.userservice.facade;

import com.james.userservice.dto.JobApplicationRequest;
import com.james.userservice.response.BaseResponse;
import com.james.userservice.response.PaginationResponse;
import com.james.userservice.response.ProfileResponse;
import com.james.userservice.response.UserResponse;
import com.james.userservice.resquest.*;
import java.util.List;

public interface UserFacade {

  void signUp(SignUpUserRequest upsertUserRequest);

  void updateProfile(UpdateUserRequest request);

  BaseResponse<ProfileResponse> getProfile();

  void inviteWatchingMovie(InviteWatchingMovieRequest request);

  void changeLocation(ChangeLocationRequest request);

  void jobApplication(JobApplicationRequest request);

  void changeRole(ChangeRoleRequest request);

  BaseResponse<PaginationResponse<UserResponse>> getByFilter(UserCriteria userCriteria);

  List<String> getAllHobbies(Long id);
}
