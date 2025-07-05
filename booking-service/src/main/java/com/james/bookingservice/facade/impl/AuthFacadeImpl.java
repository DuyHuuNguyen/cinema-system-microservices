package com.james.bookingservice.facade.impl;

import com.james.bookingservice.config.SecurityUserDetails;
import com.james.bookingservice.facade.AuthFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.resquest.AuthenticationRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
  private final AuthenticationManager authenticationManager;
  private final String ROLE_PATTERN = "ROLE_%s";

  @Override
  public BaseResponse<Void> authentication(AuthenticationRequest authenticationRequest) {
    log.info(authenticationRequest.toString());

    List<GrantedAuthority> authorityList =
        authenticationRequest.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(String.format(ROLE_PATTERN, role.toString())))
            .collect(Collectors.toList());
    log.info("   List<GrantedAuthority> authorityList = {}", authorityList.toArray());
    SecurityUserDetails principle =
        SecurityUserDetails.build(authenticationRequest.getUserDTO(), authorityList);

    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(principle, null, authorityList);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    log.info("test {}", principal.getAuthorities().toArray());
    log.info("test {}", SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
    log.info("Authentication success");
    return BaseResponse.ok();
  }
}
