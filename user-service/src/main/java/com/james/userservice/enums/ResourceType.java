package com.james.userservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResourceType {
  IMAGE("image"),
  VIDEO("video");

  private final String fileExtensions;

  public boolean isImage() {
    return this == IMAGE;
  }

  public boolean isVideo() {
    return this == VIDEO;
  }
}
