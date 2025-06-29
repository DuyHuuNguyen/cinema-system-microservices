package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.repository.RoomRepository;
import com.james.scheduleservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
  private final RoomRepository roomRepository;
}
