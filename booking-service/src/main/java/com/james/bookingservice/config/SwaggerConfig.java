package com.james.bookingservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = {@Server(url = "http://localhost:9090/booking-service")})
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer",
    in = SecuritySchemeIn.HEADER,
    paramName = "Authorization")
public class SwaggerConfig {
  @Bean
  public GroupedOpenApi openApi() {
    String[] paths = {"/api/**"};
    return GroupedOpenApi.builder()
        .group("booking-service")
        .packagesToScan("com.james.bookingservice.controller")
        .pathsToMatch(paths)
        .build();
  }
}
