package com.james.scheduleservice.exception;

import com.james.scheduleservice.enums.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class PermissionDeniedException extends RuntimeException {
  private final String errorCode;
  private final String description;

  public PermissionDeniedException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode.getCode();
    this.description = errorCode.getMessage();
  }
}
