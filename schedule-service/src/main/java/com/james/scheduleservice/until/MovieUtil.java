package com.james.scheduleservice.until;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieUtil {
  private Long id;
  private String name;
  private String firstImage;
  private Long duration;
}
