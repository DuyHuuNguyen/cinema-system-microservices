package com.james.notificationservice.interceptor;

import com.james.notificationservice.exception.*;
import com.james.notificationservice.response.BaseResponse;
import com.james.notificationservice.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestHandlerException {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<BaseResponse<ExceptionResponse>> handleEntityNotFoundException(
      EntityNotFoundException exception) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(exception.getErrorCode(), exception.getMessage()), false),
        HttpStatus.NOT_FOUND);
  }
}
