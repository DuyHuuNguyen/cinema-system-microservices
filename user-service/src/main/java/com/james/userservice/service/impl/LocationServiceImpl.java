package com.james.userservice.service.impl;

import com.james.userservice.repository.LocationRepository;
import com.james.userservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
  private final LocationRepository locationRepository;
}
