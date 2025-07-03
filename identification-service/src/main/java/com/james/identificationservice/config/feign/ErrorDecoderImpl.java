package com.james.identificationservice.config.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class ErrorDecoderImpl implements ErrorDecoder {
  @Override
  public Exception decode(String s, Response response) {
    HttpStatus responseStatus = HttpStatus.valueOf(response.status());
    if (responseStatus.is4xxClientError()) {
      return new RuntimeException("Error feign client");
    }
    return new Exception();
  }
}
