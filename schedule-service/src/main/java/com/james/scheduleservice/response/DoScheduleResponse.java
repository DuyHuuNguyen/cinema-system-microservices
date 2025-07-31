package com.james.scheduleservice.response;

import com.james.scheduleservice.dto.AllocateScreeningDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DoScheduleResponse {
  private List<AllocateScreeningDTO> allocateScreeningDTOS;
}
