package kr.hs.entrydsm.user.usecase;

import kr.hs.entrydsm.user.usecase.dto.request.AccountRequest;
import kr.hs.entrydsm.user.usecase.dto.request.SignupRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<AccessTokenResponse> auth(AccountRequest accountRequest);
    ResponseEntity<AccessTokenResponse> registerUser(SignupRequest signupRequest);
}
