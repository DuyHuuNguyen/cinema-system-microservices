package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.entity.Room;
import com.james.scheduleservice.repository.RoomRepository;
import com.james.scheduleservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
  private final RoomRepository roomRepository;

  @Override
  public Page<Room> getAllRooms(Specification<Room> specification, Pageable pageable) {
    return this.roomRepository.findAll(specification, pageable);
  }
}
