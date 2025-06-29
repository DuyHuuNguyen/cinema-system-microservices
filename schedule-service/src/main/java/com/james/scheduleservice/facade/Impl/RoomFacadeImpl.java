package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.facade.RoomFacade;
import com.james.scheduleservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomFacadeImpl implements RoomFacade {
  private final RoomService roomService;
}
