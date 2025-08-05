package com.james.bookingservice.config;

import com.james.bookingservice.interceptor.AuthenticationTokenInterceptor;
import com.james.bookingservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableSpringConfigured
@RequiredArgsConstructor
public class SecurityConfig {

  private final AuthService authService;
  private final String[] WHITE_LISTS = {
    "/api/v1/auth/login",
    "/api/v1/auth/authorization",
    "/swagger-ui/**",
    "/v3/api-docs/**",
    "/api/v1/auth",
    "/api/v1/bookings/internal",
    "/api/v1/tickets/internal"
  };

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
      throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public AuthenticationTokenInterceptor authenticationTokenInterceptor() {
    return new AuthenticationTokenInterceptor(authService);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            request ->
                request.requestMatchers(WHITE_LISTS).permitAll().anyRequest().authenticated());
    http.addFilterBefore(
        authenticationTokenInterceptor(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
