package com.james.scheduleservice.enums;

public enum MediaType {
  IMAGE,
  VIDEO;

  public boolean isImage() {
    return this == IMAGE;
  }
}
