package com.james.identificationservice.facade;

import com.james.identificationservice.request.AuthorizeRequest;
import com.james.identificationservice.request.LoginRequest;
import com.james.identificationservice.response.BaseResponse;
import com.james.identificationservice.response.LoginResponse;

public interface AuthFacade {
  BaseResponse<LoginResponse> login(LoginRequest request);

  BaseResponse<Void> authorizeRequest(AuthorizeRequest request);
}
