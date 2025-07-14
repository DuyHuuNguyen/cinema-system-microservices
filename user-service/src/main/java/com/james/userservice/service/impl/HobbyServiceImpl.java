package com.james.userservice.service.impl;

import com.james.userservice.entity.Hobby;
import com.james.userservice.enums.HobbyEnum;
import com.james.userservice.repository.HobbyRepository;
import com.james.userservice.service.HobbyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HobbyServiceImpl implements HobbyService {
  private final HobbyRepository hobbyRepository;

  @Override
  public List<Hobby> findHobbiesByHobbyEnums(List<HobbyEnum> hobbies) {
    return this.hobbyRepository.findHobbiesByEnums(hobbies);
  }
}
