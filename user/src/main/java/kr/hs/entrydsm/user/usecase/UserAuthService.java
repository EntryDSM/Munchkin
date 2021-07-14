package kr.hs.entrydsm.user.usecase;

import kr.hs.entrydsm.user.usecase.dto.request.AccountRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;

public interface UserAuthService {
    AccessTokenResponse auth(AccountRequest accountRequest);
    AccessTokenResponse refreshToken(String refreshToken);
}
