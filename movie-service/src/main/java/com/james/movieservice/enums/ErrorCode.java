package com.james.movieservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  MOVIE_NOT_FOUND("2222", "Movie not found"),
  CATEGORY_NOT_FOUND("2223", "Category not found"),
  NOT_ADMIN_THEATER("2224", "Permission denied admin theater"),
  THEATER_NOT_FOUND("2225", "Theater not found"),
  MOVIE_RATE_NOT_FOUND("2226", "Movie rate not found"),
  NOT_OWNER("2227", "User is not an owner it");
  private final String code;
  private final String message;
}
