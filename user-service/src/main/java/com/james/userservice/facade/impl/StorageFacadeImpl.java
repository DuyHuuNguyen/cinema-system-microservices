package com.james.userservice.facade.impl;

import com.james.userservice.enums.ResourceType;
import com.james.userservice.facade.StoreFileFacade;
import com.james.userservice.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageFacadeImpl implements StoreFileFacade {
  private final CloudinaryService cloudinaryService;

  @Override
  public String uploadImage(byte[] bytes, ResourceType resourceType) {
    return this.cloudinaryService.uploadFile(bytes, resourceType);
  }
}
