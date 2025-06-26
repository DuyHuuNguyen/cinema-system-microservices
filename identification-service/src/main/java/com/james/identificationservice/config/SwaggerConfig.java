package com.james.identificationservice.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public GroupedOpenApi openApi() {
    String[] paths = {"/api/**"};
    return GroupedOpenApi.builder()
        .group("identification-service")
        .packagesToScan("com.james.identificationservice.controller")
        .pathsToMatch(paths)
        .build();
  }
}
