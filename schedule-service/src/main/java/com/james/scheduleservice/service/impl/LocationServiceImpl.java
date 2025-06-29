package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.repository.LocationRepository;
import com.james.scheduleservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
  private final LocationRepository locationRepository;
}
