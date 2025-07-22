package com.james.scheduleservice.dto;

import com.james.movieservice.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AssetDTO {
  private String mediaKey;
  private MediaType mediaType;
}
