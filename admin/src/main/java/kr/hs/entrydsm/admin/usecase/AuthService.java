package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.usecase.dto.AccessTokenResponse;
import kr.hs.entrydsm.admin.usecase.dto.SignInRequest;
import kr.hs.entrydsm.admin.usecase.dto.TokenResponse;

public interface AuthService {
    TokenResponse login(SignInRequest signInRequest);
    AccessTokenResponse tokenRefresh(String receivedToken);
}
