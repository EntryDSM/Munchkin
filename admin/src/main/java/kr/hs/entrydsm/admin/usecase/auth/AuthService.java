package kr.hs.entrydsm.admin.usecase.auth;

import kr.hs.entrydsm.admin.usecase.dto.account.SignUpRequest;
import kr.hs.entrydsm.admin.usecase.dto.account.AccessTokenResponse;
import kr.hs.entrydsm.admin.usecase.dto.account.SignInRequest;
import kr.hs.entrydsm.admin.usecase.dto.account.TokenResponse;

public interface AuthService {
    void signUp(SignUpRequest request);
    TokenResponse login(SignInRequest signInRequest);
    AccessTokenResponse tokenRefresh(String receivedToken);
    String checkPassword(String password);
}
