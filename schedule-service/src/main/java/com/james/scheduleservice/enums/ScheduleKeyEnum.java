package com.james.scheduleservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScheduleKeyEnum {
  SCHEDULE_KEY("SCHEDULE_CREATED_WITH_THEATER_ID_%s_AND_DATE_%s");
  private final String key;
}
