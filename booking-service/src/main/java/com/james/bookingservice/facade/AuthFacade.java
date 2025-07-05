package com.james.bookingservice.facade;

import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.resquest.AuthenticationRequest;

public interface AuthFacade {
  BaseResponse<Void> authentication(AuthenticationRequest authenticationRequest);
}
