package com.james.userservice.facade;

import com.james.userservice.resquest.UpsertUserRequest;

public interface UserFacade {

  void signUp(UpsertUserRequest upsertUserRequest);
}
