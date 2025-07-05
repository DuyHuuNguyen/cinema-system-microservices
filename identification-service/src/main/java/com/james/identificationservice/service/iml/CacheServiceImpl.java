package com.james.identificationservice.service.iml;

import com.james.identificationservice.service.CacheService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {
  private final RedisTemplate<String, Object> redisTemplate;

  //  @PostConstruct
  //  void run(){
  //    this.store("a","11111");
  //  }

  @Override
  public void store(String key, Object value, Integer timeOut, TimeUnit timeUnit) {
    this.redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
  }

  @Override
  public void store(String key, Object value) {
    this.redisTemplate.opsForValue().set(key, value);
  }

  @Override
  public Object retrieve(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  @Override
  public void delete(String key) {
    this.redisTemplate.delete(key);
  }

  @Override
  public Boolean hasKey(String key) {
    return this.redisTemplate.hasKey(key);
  }
}
