package kr.hs.entrydsm.user.presenter.web;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.usecase.UserService;
import kr.hs.entrydsm.user.usecase.dto.request.SignupRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@Published
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<AccessTokenResponse> registerUser(@RequestBody SignupRequest signupRequest) {
        return userService.registerUser(signupRequest);
    }

}
