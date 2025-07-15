package com.james.identificationservice.facade;

import com.james.identificationservice.request.ForgotPasswordRequest;
import com.james.identificationservice.request.LoginRequest;
import com.james.identificationservice.request.RefreshTokenRequest;
import com.james.identificationservice.request.VerifyOTPRequest;
import com.james.identificationservice.response.*;

public interface AuthFacade {
  BaseResponse<LoginResponse> login(LoginRequest request);

  BaseResponse<Void> authorizeRequest(String accessToken);

  ValidTokenResponse validToken(String accessToken);

  BaseResponse<RefreshTokenResponse> refreshToken(RefreshTokenRequest request);

  void logout();

  void forgotPassword(ForgotPasswordRequest request);

  BaseResponse<VerifyOTPResponse> verifyOTP(VerifyOTPRequest request);
}
