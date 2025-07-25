package com.james.scheduleservice.resquest;

import com.james.scheduleservice.dto.LocationCriteriaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterCriteria extends BaseCriteria {
  private String name;
  private LocationCriteriaDTO locationCriteriaDTO;
}
