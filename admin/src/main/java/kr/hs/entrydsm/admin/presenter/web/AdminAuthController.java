package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.auth.AuthService;
import kr.hs.entrydsm.admin.usecase.dto.request.SignUpRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.AccessTokenResponse;
import kr.hs.entrydsm.admin.usecase.dto.request.SignInRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.TokenResponse;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/admin/auth")
@Published
@RestController
public class AdminAuthController {

    private final AuthService authService;

    //어드민 계정 만들기
    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid SignUpRequest request) {
        authService.signUp(request);
    }

    //어드민 로그인
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(@RequestBody SignInRequest signInRequest) {
        return authService.login(signInRequest);
    }

    //어드민 토큰 리프레시
    @AdminJWTRequired
    @PutMapping
    public AccessTokenResponse tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.tokenRefresh(refreshToken);
    }

    @AdminJWTRequired
    @GetMapping
    public String checkPassword(@RequestParam String password) {
        return authService.checkPassword(password);
    }

}
