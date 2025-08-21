package com.james.bookingservice.facade;

import com.james.bookingservice.request.AuthenticationRequest;
import com.james.bookingservice.response.BaseResponse;

public interface AuthFacade {
  BaseResponse<Void> authentication(AuthenticationRequest authenticationRequest);
}
