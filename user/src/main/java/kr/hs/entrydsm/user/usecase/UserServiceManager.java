package kr.hs.entrydsm.user.usecase;

import kr.hs.entrydsm.common.context.auth.token.JWTTokenProvider;
import kr.hs.entrydsm.user.domain.entity.User;
import kr.hs.entrydsm.user.domain.repository.UserRepository;
import kr.hs.entrydsm.user.usecase.dto.request.AccountRequest;
import kr.hs.entrydsm.user.usecase.dto.response.AccessTokenResponse;
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

    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider tokenProvider;

    @Override
    public ResponseEntity<AccessTokenResponse> auth(AccountRequest accountRequest) {
        return userRepository.findByTelephoneNumber(accountRequest.getPhoneNumber())
                .filter(user -> passwordEncoder.matches(accountRequest.getPassword(), user.getPassword()))
                .map(User::getReceiptCode)
                .map(receiptCode -> {
                    String accessToken = tokenProvider.generateAccessToken(receiptCode);
                    String refreshToken = tokenProvider.generateRefreshToken(receiptCode);
                    HttpHeaders headers = new HttpHeaders();
                    headers.set("Set-Cookie", refreshToken);

                    return ResponseEntity.ok()
                            .headers(headers)
                            .body(new AccessTokenResponse(accessToken));
                })
                .orElseThrow(UserNotFoundException::new);
    }

}
