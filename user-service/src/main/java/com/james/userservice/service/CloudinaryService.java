package com.james.userservice.service;

import com.james.userservice.enums.ResourceType;

public interface CloudinaryService {
  String uploadFile(byte[] image, ResourceType resourceType);
}
