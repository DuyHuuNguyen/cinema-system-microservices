package com.james.movieservice.dto;

import com.james.movieservice.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RateAssetDTO {
  private String mediaKey;
  private MediaType mediaType;
}
