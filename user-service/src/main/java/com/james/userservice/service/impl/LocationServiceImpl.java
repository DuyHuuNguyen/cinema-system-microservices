package com.james.userservice.service.impl;

import com.james.userservice.entity.Location;
import com.james.userservice.repository.LocationRepository;
import com.james.userservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
  private final LocationRepository locationRepository;

  @Override
  public void save(Location location) {
    this.locationRepository.save(location);
  }
}
