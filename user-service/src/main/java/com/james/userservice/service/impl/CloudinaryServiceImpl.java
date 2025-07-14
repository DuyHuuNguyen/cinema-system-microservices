package com.james.userservice.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.james.userservice.enums.ResourceType;
import com.james.userservice.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
  private final Cloudinary cloudinary;

  @Override
  @SneakyThrows
  public String uploadFile(byte[] image, ResourceType resourceType) {
    var params =
        ObjectUtils.asMap("folder", "users", "resource_type", resourceType.getFileExtensions());
    var uploadResult = cloudinary.uploader().upload(image, params);
    return uploadResult.get("secure_url").toString();
  }
}
