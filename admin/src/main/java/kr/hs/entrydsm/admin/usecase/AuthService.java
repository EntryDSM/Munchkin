package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.usecase.dto.response.AccessTokenResponse;
import kr.hs.entrydsm.admin.usecase.dto.request.SignInRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.TokenResponse;

public interface AuthService {
    TokenResponse login(SignInRequest signInRequest);
    AccessTokenResponse tokenRefresh(String receivedToken);
}
