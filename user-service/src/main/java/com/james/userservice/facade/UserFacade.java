package com.james.userservice.facade;

import com.james.userservice.resquest.SignUpUserRequest;
import com.james.userservice.resquest.UpdateUserRequest;

public interface UserFacade {

  void signUp(SignUpUserRequest upsertUserRequest);

  void updateProfile(UpdateUserRequest request);
}
