package kr.hs.entrydsm.user.presenter.web;

import kr.hs.entrydsm.common.context.auth.token.JWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.usecase.UserService;
import kr.hs.entrydsm.user.usecase.dto.request.AccountRequest;
import kr.hs.entrydsm.user.usecase.dto.request.AuthCodeRequest;
import kr.hs.entrydsm.user.usecase.dto.request.EmailRequest;
import kr.hs.entrydsm.user.usecase.dto.request.SignupRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
import kr.hs.entrydsm.user.usecase.dto.response.UserStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@Published
@RestController
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccessTokenResponse registerUser(@RequestBody @Valid SignupRequest signupRequest) {
        return userService.registerUser(signupRequest);
    }

    @JWTRequired
    @GetMapping("/status")
    public UserStatusResponse getUserStatus() {
        return userService.getUserStatus();
    }

    @PutMapping("/password")
    public void changePassword(@RequestBody @Valid AccountRequest accountRequest) {
        userService.changePassword(accountRequest);
    }

    @PostMapping("/password/email/verify")
    public void sendPasswordAuthCode(@RequestBody @Valid EmailRequest emailRequest) {
        userService.sendPasswordAuthCode(emailRequest);
    }

    @PostMapping("/email/verify")
    public void sendAuthCode(@RequestBody @Valid EmailRequest emailRequest) {
        userService.sendAuthCode(emailRequest);
    }

    @PutMapping("/email/verify")
    public void verifyAuthCode(@RequestBody AuthCodeRequest authCodeRequest) {
        userService.verifyAuthCode(authCodeRequest);
    }

}
