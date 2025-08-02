package com.james.scheduleservice.service;

import com.james.scheduleservice.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface RoomService {
  Page<Room> getAllRooms(Specification<Room> specification, Pageable pageable);
}
