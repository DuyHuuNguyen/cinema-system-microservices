package com.james.identificationservice.service;

public interface CacheService {
  Boolean hasKey(String accessTokenCacheKey);

  String retrieve(String accessTokenCacheKey);
}
