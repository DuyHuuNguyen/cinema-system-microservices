package com.james.userservice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InviteWatchingMovieDTO {
  private List<EmailDTO> emailDTOS;
  private ScheduleDTO scheduleDTO;
}
