package com.james.paymentservice.service.impl;

import com.james.paymentservice.service.IdempotencyService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdempotencyServiceImpl implements IdempotencyService {
  private final RedisTemplate<String, Object> redisTemplate;

  @Override
  public void storage(String idempotencyKey) {
    redisTemplate.opsForValue().set(idempotencyKey, UUID.randomUUID().toString());
  }

  @Override
  public Boolean hasIdempotencyKey(String idempotencyKey) {
    return redisTemplate.hasKey(idempotencyKey);
  }
}
