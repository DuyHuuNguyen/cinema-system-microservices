package com.james.identificationservice.facade;

import com.james.identificationservice.request.*;
import com.james.identificationservice.response.*;

public interface AuthFacade {
  BaseResponse<LoginResponse> login(LoginRequest request);

  BaseResponse<Void> authorizeRequest(String accessToken);

  ValidTokenResponse validToken(String accessToken);

  BaseResponse<RefreshTokenResponse> refreshToken(RefreshTokenRequest request);

  void logout();

  void forgotPassword(ForgotPasswordRequest request);

  BaseResponse<VerifyOTPResponse> verifyOTP(VerifyOTPRequest request);

  void resetPassword(ResetPasswordRequest request);
}
