package kr.hs.entrydsm.user.usecase;

import kr.hs.entrydsm.common.context.auth.token.JWTTokenProvider;
import kr.hs.entrydsm.user.entity.authcode.AuthCode;
import kr.hs.entrydsm.user.entity.authcode.AuthCodeLimit;
import kr.hs.entrydsm.user.entity.authcode.AuthCodeLimitRedisRepository;
import kr.hs.entrydsm.user.entity.authcode.AuthCodeRedisRepository;
import kr.hs.entrydsm.user.entity.status.Status;
import kr.hs.entrydsm.user.entity.status.StatusRepository;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.UserRepository;
import kr.hs.entrydsm.user.usecase.dto.request.AccountRequest;
import kr.hs.entrydsm.user.usecase.dto.request.AuthCodeRequest;
import kr.hs.entrydsm.user.usecase.dto.request.PhoneNumberRequest;
import kr.hs.entrydsm.user.usecase.dto.request.SignupRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
import kr.hs.entrydsm.user.usecase.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserServiceManager implements UserAuthService, UserService {

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final AuthCodeRedisRepository authCodeRepository;
    private final AuthCodeLimitRedisRepository authCodeLimitRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider tokenProvider;

    @Value("${authcode.length}")
    private int authCodeLength;

    @Value("${authcode.ttl}")
    private long authCodeTtl;

    @Value("${authcode.limit}")
    private int authCodeLimit;

    @Value("${authcode.limit-ttl}")
    private int authCodeLimitTtl;

    @Value("${auth.jwt.exp.refresh}")
    private long refreshTokenExpiration;

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

        authCodeRepository.findById(phoneNumber)
                .filter(authCode -> authCode.getCode().equals(code))
                .filter(AuthCode::isVerified)
                .orElseThrow(InvalidAuthCodeException::new);

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

    @Override
    public void sendAuthCode(PhoneNumberRequest phoneNumberRequest) {
        String phoneNumber = phoneNumberRequest.getPhoneNumber();
        boolean isRegisteredUser =  userRepository.existsByTelephoneNumber(phoneNumber);

        if (isRegisteredUser)throw new UserAlreadyExistsException();
        if (isOverRequestLimit(phoneNumber)) throw new AuthCodeRequestOverLimitException();

        String randomCode = randomCode();
        authCodeRepository.findById(phoneNumber)
                .or(() -> Optional.of(new AuthCode(phoneNumber, randomCode, false, authCodeTtl)))
                .map(authCode -> authCodeRepository.save(authCode.updateAuthCode(randomCode, authCodeTtl)));
    }

    @Override
    public void verifyAuthCode(AuthCodeRequest authCodeRequest) {
        String phoneNumber = authCodeRequest.getPhoneNumber();
        String code = authCodeRequest.getCode();;

        authCodeRepository.findById(phoneNumber)
                .filter(authCode -> authCode.getCode().equals(code))
                .map(authCode -> {
                    if (authCode.isVerified()) throw new AuthCodeAlreadyVerifiedException();
                    return authCodeRepository.save(authCode.verify());
                })
                .orElseThrow(InvalidAuthCodeException::new);
    }

    private ResponseEntity<AccessTokenResponse> getAccessToken(long receiptCode) {
        String accessToken = tokenProvider.generateAccessToken(receiptCode);
        String refreshToken = tokenProvider.generateRefreshToken(receiptCode);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie",
                String.format("refresh-token=%s; Expires=%s; HttpOnly; Path=/", refreshToken, getExpireDateByString()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new AccessTokenResponse(accessToken));
    }

    private String getExpireDateByString() {
        Date date = new Date(System.currentTimeMillis() + (refreshTokenExpiration * 1000));
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
        return format.format(date);
    }

    private String randomCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < authCodeLength; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    private boolean isOverRequestLimit(String phoneNumber) {
        return authCodeLimitRepository.findById(phoneNumber)
                .or(() -> Optional.of(new AuthCodeLimit(phoneNumber, 0, authCodeLimitTtl)))
                .filter(limit -> limit.getCount() < authCodeLimit)
                .map(limit -> authCodeLimitRepository.save(limit.updateAuthCode(authCodeLimitTtl)))
                .isEmpty();
    }

}
