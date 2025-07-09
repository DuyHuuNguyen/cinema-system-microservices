package com.james.userservice.facade;

import com.james.userservice.dto.JobApplicationRequest;
import com.james.userservice.response.BaseResponse;
import com.james.userservice.response.ProfileResponse;
import com.james.userservice.resquest.*;

public interface UserFacade {

  void signUp(SignUpUserRequest upsertUserRequest);

  void updateProfile(UpdateUserRequest request);

  BaseResponse<ProfileResponse> getProfile();

  void inviteWatchingMovie(InviteWatchingMovieRequest request);

  void changeLocation(ChangeLocationRequest request);

  void jobApplication(JobApplicationRequest request);

  void changeRole(ChangeRoleRequest request);
}
