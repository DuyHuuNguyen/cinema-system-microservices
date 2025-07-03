package com.james.identificationservice.mapper;

import com.james.identificationservice.dto.UserDTO;
import com.james.identificationservice.entity.User;

public interface UserMapper {
  UserDTO toUserDTO(User user);
}
