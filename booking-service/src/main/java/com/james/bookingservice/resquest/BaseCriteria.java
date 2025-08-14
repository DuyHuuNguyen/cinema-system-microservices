package com.james.bookingservice.resquest;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseCriteria {
  private int currentPage;
  private int pageSize;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
