package com.james.bookingservice.facade.impl;

import com.james.bookingservice.config.SecurityUserDetails;
import com.james.bookingservice.facade.AuthFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.resquest.AuthenticationRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
  private final AuthenticationManager authenticationManager;
  private final String ROLE_PATTERN = "ROLE_%s";

  @Override
  public BaseResponse<Void> authentication(AuthenticationRequest authenticationRequest) {
    List<GrantedAuthority> authorityList =
        authenticationRequest.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(String.format(ROLE_PATTERN, role.toString())))
            .collect(Collectors.toList());

    SecurityUserDetails principle =
        SecurityUserDetails.build(authenticationRequest.getUserDTO(), authorityList);

    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(principle, null, authorityList);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    return BaseResponse.ok();
  }
}
