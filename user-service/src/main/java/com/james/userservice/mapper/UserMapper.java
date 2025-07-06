package com.james.userservice.mapper;

import com.james.userservice.entity.User;
import com.james.userservice.response.ProfileResponse;

public interface UserMapper {
  ProfileResponse toProfile(User user);
}
