package com.james.userservice.facade;

import com.james.userservice.enums.ResourceType;

public interface StorageFacade {
  String uploadImage(byte[] bytes, ResourceType resourceType);
}
