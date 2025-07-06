package com.james.userservice.resquest;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InviteWatchingMovieRequest {
  private List<Long> ids;
  private Long scheduleId;
}
