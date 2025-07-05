package com.james.userservice.service;

import com.james.userservice.entity.Hobby;
import com.james.userservice.enums.HobbyEnum;
import java.util.List;

public interface HobbyService {
  List<Hobby> findHobbiesByHobbyEnums(List<HobbyEnum> hobbies);
}
