package com.james.identificationservice.service;

public interface JwtService {
  String generateAccessToken(String email);

  String generateRefreshToken(String email);

  Boolean validateToken(String token);

  String getEmailFromJwtToken(String token);

  String generateResetPasswordToken(String email);
}
