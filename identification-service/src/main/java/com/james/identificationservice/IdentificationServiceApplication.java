package com.james.identificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// @EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class IdentificationServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(IdentificationServiceApplication.class, args);
  }
}
