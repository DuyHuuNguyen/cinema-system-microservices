package com.james.scheduleservice.until;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeConverter {
  public static LocalTime convertStringToTime(String timeStr) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      return LocalTime.parse(timeStr, formatter);
    } catch (DateTimeParseException e) {
      System.err.println("Invalid time format: " + timeStr);
      return null;
    }
  }

  public static LocalDateTime convertStringToDateTime(Long time) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
  }
}
