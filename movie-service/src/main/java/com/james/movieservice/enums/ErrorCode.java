package com.james.movieservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  MOVIE_NOT_FOUND("1111", "MOVIE not found");

  private final String code;
  private final String message;
}
