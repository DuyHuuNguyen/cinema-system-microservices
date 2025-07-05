package com.james.userservice.service.impl;

import com.cloudinary.Cloudinary;
import com.james.userservice.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
  private final Cloudinary cloudinary;
}
