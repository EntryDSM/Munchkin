package kr.hs.entrydsm.user.usecase;

import kr.hs.entrydsm.user.usecase.dto.request.AccountRequest;
import kr.hs.entrydsm.user.usecase.dto.request.AuthCodeRequest;
import kr.hs.entrydsm.user.usecase.dto.request.EmailRequest;
import kr.hs.entrydsm.user.usecase.dto.request.SignupRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
import kr.hs.entrydsm.user.usecase.dto.response.UserStatusResponse;

public interface UserService {
    AccessTokenResponse registerUser(SignupRequest signupRequest);
    UserStatusResponse getUserStatus();
    void changePassword(AccountRequest accountRequest);
    void sendPasswordAuthCode(EmailRequest emailRequest);
    void sendAuthCode(EmailRequest emailRequest);
    void verifyAuthCode(AuthCodeRequest authCodeRequest);
    void submitFinally();
}
