package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.entity.refreshtoken.AdminRefreshToken;
import kr.hs.entrydsm.admin.security.JwtTokenProvider;
import kr.hs.entrydsm.admin.usecase.dto.account.SignInRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public abstract class AdminBaseTest {

    protected static final PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            return (String) rawPassword;
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return rawPassword.equals(encodedPassword);
        }
    };

    protected static final Admin TEACHER_ADMIN = Admin.builder()
            .id("asdf1234")
            .password(passwordEncoder.encode("teacheradmin"))
            .name("본부교무실")
            .build();

    protected static final JwtTokenProvider JWT_TOKEN_PROVIDER = new MockJwtTokenProvider();

    protected static final AdminRefreshToken REFRESH_TOKEN = new AdminRefreshToken(TEACHER_ADMIN.getId(), JWT_TOKEN_PROVIDER.generateRefreshToken(TEACHER_ADMIN.getId()), 123456L);

    private static final List<Admin> ADMINS = new ArrayList<>();
    static {
        ADMINS.add(TEACHER_ADMIN);
    }

    protected static final SignInRequest SIGN_IN_REQUEST = SignInRequest.builder()
            .id("asdf1234")
            .password("123456")
            .build();

    protected static boolean addAccount(Admin admin) {
        boolean exists = ADMINS.stream().anyMatch(u -> u.getId().equals(admin.getId()));
        boolean requestCheck = admin.getId().length() > 8 || admin.getName().length() > 5;

        if (exists) return false;
        if(requestCheck) return false;

        ADMINS.add(admin);
        return true;
    }
}
