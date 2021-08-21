package kr.hs.entrydsm.user.usecase;

import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import kr.hs.entrydsm.common.context.auth.token.JWTTokenProvider;
import kr.hs.entrydsm.common.context.sender.ContentSender;
import kr.hs.entrydsm.user.entity.authcode.AuthCode;
import kr.hs.entrydsm.user.entity.authcode.AuthCodeLimit;
import kr.hs.entrydsm.user.entity.authcode.AuthCodeLimitRedisRepository;
import kr.hs.entrydsm.user.entity.authcode.AuthCodeRedisRepository;
import kr.hs.entrydsm.user.entity.refreshtoken.RefreshToken;
import kr.hs.entrydsm.user.entity.refreshtoken.RefreshTokenRepository;
import kr.hs.entrydsm.user.entity.status.Status;
import kr.hs.entrydsm.user.entity.status.StatusRepository;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.UserRepository;
import kr.hs.entrydsm.user.usecase.dto.request.AccountRequest;
import kr.hs.entrydsm.user.usecase.dto.request.AuthCodeRequest;
import kr.hs.entrydsm.user.usecase.dto.request.EmailRequest;
import kr.hs.entrydsm.user.usecase.dto.request.SignupRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
import kr.hs.entrydsm.user.usecase.dto.response.UserStatusResponse;
import kr.hs.entrydsm.user.usecase.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserServiceManager implements UserAuthService, UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final StatusRepository statusRepository;
    private final AuthCodeRedisRepository authCodeRepository;
    private final AuthCodeLimitRedisRepository authCodeLimitRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider tokenProvider;
    private final ContentSender contentSender;

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
    public AccessTokenResponse auth(AccountRequest accountRequest) {
        return userRepository.findByEmail(accountRequest.getEmail())
                .filter(user -> passwordEncoder.matches(accountRequest.getPassword(), user.getPassword()))
                .map(User::getReceiptCode)
                .map(this::getAccessToken)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public AccessTokenResponse refreshToken(String refreshToken) {
        if (tokenProvider.validateToken(refreshToken) && tokenProvider.isRefreshToken(refreshToken)) {
            long receiptCode = tokenProvider.parseRefreshToken(refreshToken);
            return refreshTokenRepository.findById(receiptCode)
                    .map(token -> getAccessToken(token.getReceiptCode()))
                    .orElseThrow(ExpiredRefreshTokenException::new);
        }
        throw new InvalidTokenException();
    }

    @Override
    public AccessTokenResponse registerUser(SignupRequest signupRequest) {
        String email = signupRequest.getEmail();
        String name = signupRequest.getName();
        String password = signupRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        authCodeRepository.findById(email)
                .filter(AuthCode::isVerified)
                .orElseThrow(InvalidAuthCodeException::new);

        User user = userRepository.save(
                User.builder()
                        .email(email)
                        .name(name)
                        .password(encodedPassword)
                        .selfIntroduce("")
                        .studyPlan("")
                        .isDaejeon(null)
                        .build()
        );

        statusRepository.save(
                Status.builder()
                        .receiptCode(user.getReceiptCode())
                        .isPrintedArrived(false)
                        .isSubmit(false)
                        .isFirstRoundPass(false)
                        .build()
        );

        return getAccessToken(user.getReceiptCode());
    }

    @Override
    public UserStatusResponse getUserStatus() {
        long receiptCode = AuthenticationManager.getUserReceiptCode();
        User user = userRepository.findByReceiptCode(receiptCode)
                .orElseThrow(UserNotFoundException::new);
        Status status = user.getStatus();

        return UserStatusResponse.builder()
                    .name(user.getName())
                    .phoneNumber(user.getTelephoneNumber())
                    .email(user.getEmail())
                    .isSubmit(status.isSubmit())
                    .isPrintedArrived(status.isPrintedArrived())
                    .applicationType(user.getApplicationType())
                    .selfIntroduce(user.getSelfIntroduce().length())
                    .studyPlan(user.getStudyPlan().length())
                    .build();
    }

    @Override
    public void changePassword(AccountRequest accountRequest) {
        String email = accountRequest.getEmail();
        String password = accountRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        authCodeRepository.findById(email)
                .filter(AuthCode::isVerified)
                .orElseThrow(InvalidAuthCodeException::new);

        User user = userRepository.findByEmail(email)
                .map(u -> u.changePassword(encodedPassword))
                .orElseThrow(UserNotFoundException::new);
        userRepository.save(user);
    }

    @Override
    public void sendPasswordAuthCode(EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        boolean isRegisteredUser =  userRepository.existsByEmail(email);

        if (!isRegisteredUser) throw new UserNotFoundException();
        sendRandomCode(email);
    }

    @Override
    public void sendAuthCode(EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        boolean isRegisteredUser =  userRepository.existsByEmail(email);

        if (isRegisteredUser) throw new UserAlreadyExistsException();
        sendRandomCode(email);
    }

    @Override
    public void verifyAuthCode(AuthCodeRequest authCodeRequest) {
        String email = authCodeRequest.getEmail();
        String code = authCodeRequest.getCode();

        authCodeRepository.findById(email)
                .filter(authCode -> authCode.getCode().equals(code))
                .map(authCode -> {
                    if (authCode.isVerified()) throw new AuthCodeAlreadyVerifiedException();
                    return authCodeRepository.save(authCode.verify());
                })
                .orElseThrow(InvalidAuthCodeException::new);
    }

    @Override
    public void submitFinally() {
        long receiptCode = AuthenticationManager.getUserReceiptCode();
        userRepository.findByReceiptCode(receiptCode)
                .map(User::submitFinally)
                .ifPresent(userRepository::save);
    }

    private AccessTokenResponse getAccessToken(long receiptCode) {
        String accessToken = tokenProvider.generateAccessToken(receiptCode);
        String refreshToken = tokenProvider.generateRefreshToken(receiptCode);
        refreshTokenRepository.findById(receiptCode)
                .or(() -> Optional.of(new RefreshToken(receiptCode, refreshToken, refreshTokenExpiration)))
                .ifPresent(token -> refreshTokenRepository.save(token.update(refreshToken, refreshTokenExpiration)));

        return new AccessTokenResponse(accessToken, refreshToken);
    }

    private String randomCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < authCodeLength; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    private void sendRandomCode(String email) {
        if (isOverRequestLimit(email)) throw new AuthCodeRequestOverLimitException();

        String randomCode = randomCode();
        Map<String, String> params = new HashMap<>();
        params.put("code", randomCode);

        authCodeRepository.findById(email)
                .or(() -> Optional.of(new AuthCode(email, randomCode, false, authCodeTtl)))
                .map(authCode -> authCodeRepository.save(authCode.updateAuthCode(randomCode, authCodeTtl)))
                .ifPresent(authCode -> contentSender.sendMessage(email, "MunchkinEmailTemplate", params));
    }

    private boolean isOverRequestLimit(String email) {
        return authCodeLimitRepository.findById(email)
                .or(() -> Optional.of(new AuthCodeLimit(email, 0, authCodeLimitTtl)))
                .filter(limit -> limit.getCount() < authCodeLimit)
                .map(limit -> authCodeLimitRepository.save(limit.updateAuthCode(authCodeLimitTtl)))
                .isEmpty();
    }

}
