package com.james.identificationservice.until;

import java.util.Random;

public class GenerateCodeUntil {
  public static String generateOTP() {
    Random random = new Random();
    int code = random.nextInt(999999999);
    return String.format("%09d", code);
  }
}
