package com.james.identificationservice.facade;

import com.james.identificationservice.request.LoginRequest;
import com.james.identificationservice.response.BaseResponse;
import com.james.identificationservice.response.LoginResponse;

public interface UserFacade {
  BaseResponse<LoginResponse> login(LoginRequest request);
}
