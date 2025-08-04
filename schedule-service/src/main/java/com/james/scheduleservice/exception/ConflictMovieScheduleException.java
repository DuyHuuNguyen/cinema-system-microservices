package com.james.scheduleservice.exception;

import com.james.scheduleservice.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ConflictMovieScheduleException extends RuntimeException {
  private String errorCode;
  private String message;

  public ConflictMovieScheduleException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode.getCode();
    this.message = errorCode.getMessage();
  }
}
