package kr.hs.entrydsm.user.usecase;

import kr.hs.entrydsm.common.context.auth.token.JWTTokenProvider;
import kr.hs.entrydsm.user.entity.*;
import kr.hs.entrydsm.user.usecase.dto.request.AccountRequest;
import kr.hs.entrydsm.user.usecase.dto.request.SignupRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
import kr.hs.entrydsm.user.usecase.exception.InvalidAuthCodeException;
import kr.hs.entrydsm.user.usecase.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceManager implements UserService {

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final AuthCodeRedisRepository authCodeRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider tokenProvider;

    @Override
    public ResponseEntity<AccessTokenResponse> auth(AccountRequest accountRequest) {
        return userRepository.findByTelephoneNumber(accountRequest.getPhoneNumber())
                .filter(user -> passwordEncoder.matches(accountRequest.getPassword(), user.getPassword()))
                .map(User::getReceiptCode)
                .map(this::getAccessToken)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public ResponseEntity<AccessTokenResponse> registerUser(SignupRequest signupRequest) {
        String phoneNumber = signupRequest.getPhoneNumber();
        String code = signupRequest.getCode();
        String name = signupRequest.getName();
        String password = signupRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

//        authCodeRepository.findByPhoneNumberAndCode(phoneNumber, code)
//                .orElseThrow(InvalidAuthCodeException::new);

        User user = userRepository.save(
                User.builder()
                        .telephoneNumber(phoneNumber)
                        .name(name)
                        .password(encodedPassword)
                        .build()
        );

        statusRepository.save(
                Status.builder()
                        .receiptCode(user.getReceiptCode())
                        .isPaid(false)
                        .isPrintedArrived(false)
                        .isSubmit(false)
                        .build()
        );

        return getAccessToken(user.getReceiptCode());
    }

    private ResponseEntity<AccessTokenResponse> getAccessToken(long receiptCode) {
        String accessToken = tokenProvider.generateAccessToken(receiptCode);
        String refreshToken = tokenProvider.generateRefreshToken(receiptCode);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie", refreshToken);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new AccessTokenResponse(accessToken));
    }

}
