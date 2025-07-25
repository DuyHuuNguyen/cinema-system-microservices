package com.james.scheduleservice.dto;

import com.james.movieservice.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AssetDTO {
  private String mediaKey;
  private MediaType mediaType;
}
