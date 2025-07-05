package com.james.identificationservice.config;

import com.james.identificationservice.interceptor.UserDetailsAuthenticationProviderInterceptor;
import com.james.identificationservice.service.CacheService;
import com.james.identificationservice.service.JwtService;
import com.james.identificationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {
  private final UserService userService;
  private final JwtService jwtService;
  private final CacheService cacheService;

  private final String[] WHITE_LISTS = {"/api/v1/auth/login","/api/v1/auth/authorization",
          "/swagger-ui/**",
          "/v3/api-docs/**",};

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
      throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public UserDetailsAuthenticationProviderInterceptor
      userDetailsAuthenticationProviderInterceptor() {
    return new UserDetailsAuthenticationProviderInterceptor(
        this.userService, this.passwordEncoder());
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
    http.authenticationProvider(this.userDetailsAuthenticationProviderInterceptor());
    return http.build();
  }
}
