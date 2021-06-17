package kr.hs.entrydsm.user.presenter.web;

import kr.hs.entrydsm.common.context.auth.token.RefreshRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.usecase.UserAuthService;
import kr.hs.entrydsm.user.usecase.dto.request.AccountRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user/auth")
@Published
@RestController
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping
    public ResponseEntity<AccessTokenResponse> auth(@RequestBody @Valid AccountRequest accountRequest) {
        return userAuthService.auth(accountRequest);
    }

    @RefreshRequired
    @PutMapping
    public ResponseEntity<AccessTokenResponse> refreshToken(@CookieValue("refresh-token") String refreshToken) {
        return userAuthService.refreshToken(refreshToken);
    }

}
