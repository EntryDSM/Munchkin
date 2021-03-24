package kr.hs.entrydsm.user.usecase;

import kr.hs.entrydsm.user.usecase.dto.request.AuthCodeRequest;
import kr.hs.entrydsm.user.usecase.dto.request.PhoneNumberRequest;
import kr.hs.entrydsm.user.usecase.dto.request.SignupRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
import kr.hs.entrydsm.user.usecase.dto.response.UserStatusResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<AccessTokenResponse> registerUser(SignupRequest signupRequest);
    UserStatusResponse getUserStatus();
    void sendAuthCode(PhoneNumberRequest phoneNumberRequest);
    void verifyAuthCode(AuthCodeRequest authCodeRequest);
}
