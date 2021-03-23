package kr.hs.entrydsm.user.presenter.web;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.usecase.UserService;
import kr.hs.entrydsm.user.usecase.dto.request.AuthCodeRequest;
import kr.hs.entrydsm.user.usecase.dto.request.PhoneNumberRequest;
import kr.hs.entrydsm.user.usecase.dto.request.SignupRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AccessTokenResponse> registerUser(@RequestBody @Valid SignupRequest signupRequest) {
        return userService.registerUser(signupRequest);
    }

    @PostMapping("/phone/verify")
    public void sendAuthCode(@RequestBody @Valid PhoneNumberRequest phoneNumberRequest) {
        userService.sendAuthCode(phoneNumberRequest);
    }

    @PutMapping("/phone/verify")
    public void verifyAuthCode(@RequestBody AuthCodeRequest authCodeRequest) {
        userService.verifyAuthCode(authCodeRequest);
    }

}
