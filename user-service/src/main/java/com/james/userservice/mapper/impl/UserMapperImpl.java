package com.james.userservice.mapper.impl;

import com.james.userservice.entity.Hobby;
import com.james.userservice.entity.User;
import com.james.userservice.mapper.UserMapper;
import com.james.userservice.response.ProfileResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

  @Override
  public ProfileResponse toProfile(User user) {
    return ProfileResponse.builder()
        .firstname(user.getFirstname())
        .lastname(user.getLastname())
        .email(user.getEmail())
        .avatarKey(user.getAvatarKey())
        .dateOfBirth(user.getDateOfBirth())
        .isLoyalCustomer(user.getIsLoyalCustomer())
        .locationDTO(user.getLocationDTO())
        .hobbies(user.getHobbies().stream().map(Hobby::getHobbyName).toList())
        .build();
  }
}
