package com.james.identificationservice.mapper.impl;

import com.james.identificationservice.dto.UserDTO;
import com.james.identificationservice.entity.User;
import com.james.identificationservice.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
  @Override
  public UserDTO toUserDTO(User user) {
    return UserDTO.builder()
        .id(user.getId())
        .email(user.getEmail())
        .password(user.getPassword())
        .build();
  }
}
