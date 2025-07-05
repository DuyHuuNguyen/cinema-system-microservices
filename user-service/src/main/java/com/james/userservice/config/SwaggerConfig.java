package com.james.userservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
  @Bean
  public GroupedOpenApi openApi() {
    String[] paths = {"/api/**"};
    return GroupedOpenApi.builder()
        .group("user-service")
        .packagesToScan("com.james.userservice.controller")
        .pathsToMatch(paths)
        .build();
  }
}
