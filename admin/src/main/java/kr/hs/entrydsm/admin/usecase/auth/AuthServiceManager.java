package kr.hs.entrydsm.admin.usecase.auth;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.entity.refreshtoken.AdminRefreshToken;
import kr.hs.entrydsm.admin.entity.admin.AdminRepository;
import kr.hs.entrydsm.admin.infrastructure.database.AdminRefreshTokenRepositoryManager;
import kr.hs.entrydsm.admin.security.JwtTokenProvider;
import kr.hs.entrydsm.admin.usecase.dto.account.AccessTokenResponse;
import kr.hs.entrydsm.admin.usecase.dto.account.SignInRequest;
import kr.hs.entrydsm.admin.usecase.dto.account.TokenResponse;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.InvalidTokenException;
import kr.hs.entrydsm.admin.usecase.exception.PasswordNotValidException;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceManager implements AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    private final AdminRefreshTokenRepositoryManager refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshExp;

    @Override
    public TokenResponse login(SignInRequest signInRequest) {
        return adminRepository.findById(signInRequest.getId())
                .filter(user -> passwordEncoder.matches(signInRequest.getPassword(), user.getPassword()))
                .map(Admin::getId)
                .map(adminId -> {
                    String refreshToken = jwtTokenProvider.generateRefreshToken(adminId);
                    return new AdminRefreshToken(adminId, refreshToken, refreshExp);
                })
                .map(refreshTokenRepository::save)
                .map(refreshToken -> {
                    String accessToken = jwtTokenProvider.generateAccessToken(refreshToken.getId());
                    return new TokenResponse(accessToken, refreshToken.getRefreshToken());
                })
                .orElseThrow(AdminNotFoundException::new);
    }

    @Override
    public AccessTokenResponse tokenRefresh(String receivedToken) {
        if(!jwtTokenProvider.isRefreshToken(receivedToken)) {
            throw new InvalidTokenException();
        }

        return refreshTokenRepository.findByRefreshToken(receivedToken)
                .map(refreshToken ->refreshToken.update(refreshExp))
                .map(refreshTokenRepository::save)
                .map(refreshToken ->new AccessTokenResponse(jwtTokenProvider.generateAccessToken(refreshToken.getId())))
                .orElseThrow(AdminNotFoundException::new);
    }

    @Override
    public String checkPassword(String password) {
        Admin admin = adminRepository.findById(AuthenticationManager.getAdminId())
                .orElseThrow(AdminNotFoundException::new);

        if(!passwordEncoder.matches(password, admin.getPassword())) {
            throw new PasswordNotValidException();
        }
        return "Authorization completed.";
    }

}
