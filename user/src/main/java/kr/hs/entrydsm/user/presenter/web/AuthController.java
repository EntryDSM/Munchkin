package kr.hs.entrydsm.user.presenter.web;

import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user/auth")
@Published
@RestController
public class AuthController {
}
