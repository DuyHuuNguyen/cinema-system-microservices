package com.james.scheduleservice.exception;

import com.james.scheduleservice.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleIsDoneException extends RuntimeException {
  private final String errorCode;
  private final String description;

  public ScheduleIsDoneException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode.getCode();
    this.description = errorCode.getMessage();
  }
}
