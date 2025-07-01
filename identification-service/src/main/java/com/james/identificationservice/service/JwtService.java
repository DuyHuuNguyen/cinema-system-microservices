package com.james.identificationservice.service;

public interface JwtService {
  boolean validateToken(String token);

  String getEmailFromJwtToken(String token);
}
