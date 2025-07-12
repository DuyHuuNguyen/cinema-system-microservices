package com.james.userservice.mapper;

import com.james.userservice.entity.User;
import com.james.userservice.response.ProfileResponse;
import com.james.userservice.response.UserResponse;

public interface UserMapper {
  ProfileResponse toProfile(User user);

  UserResponse toUserResponse(User user);
}
