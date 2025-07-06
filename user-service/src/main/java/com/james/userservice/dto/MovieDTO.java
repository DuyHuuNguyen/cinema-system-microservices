package com.james.userservice.dto;

import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieDTO {
  private String title;
  private Time duration;
  private String language;
  private String poster;
  private Integer star;
}
