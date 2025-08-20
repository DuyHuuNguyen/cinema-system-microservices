package com.james.bookingservice.interceptor;

import com.james.bookingservice.exception.*;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.ExceptionResponse;
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

  @ExceptionHandler(TicketUsedException.class)
  public ResponseEntity<BaseResponse<ExceptionResponse>> handleTicketUsedException(
      TicketUsedException exception) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(exception.getErrorCode(), exception.getMessage()), false),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PermissionDeniedException.class)
  public ResponseEntity<BaseResponse<ExceptionResponse>> handlePermissionDeniedException(
      PermissionDeniedException exception) {
    return new ResponseEntity<>(
        BaseResponse.build(
            new ExceptionResponse(exception.getErrorCode(), exception.getMessage()), false),
        HttpStatus.BAD_REQUEST);
  }
}
