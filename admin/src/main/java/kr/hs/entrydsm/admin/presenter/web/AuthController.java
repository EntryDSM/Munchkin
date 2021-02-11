package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.AuthService;
import kr.hs.entrydsm.admin.usecase.dto.AccessTokenResponse;
import kr.hs.entrydsm.admin.usecase.dto.SignInRequest;
import kr.hs.entrydsm.admin.usecase.dto.TokenResponse;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin/auth")
@Published
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(SignInRequest signInRequest) {
        return authService.login(signInRequest);
    }

    @PutMapping
    public AccessTokenResponse tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.tokenRefresh(refreshToken);
    }

}
