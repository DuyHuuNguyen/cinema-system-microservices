package com.james.bookingservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActiveEnum {
  RELEASE(true),
  HIDDEN_DEFAULT(false);
  private final Boolean isActive;
}
