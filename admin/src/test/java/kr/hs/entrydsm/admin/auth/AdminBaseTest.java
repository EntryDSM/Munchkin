package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.entity.admin.AdminRepository;
import kr.hs.entrydsm.admin.infrastructure.database.AdminRefreshTokenRepositoryManager;
import kr.hs.entrydsm.admin.security.JwtTokenProvider;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

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
    protected static final AdminRepository adminRepository = new MockAdminRepositoryManager();
    protected static final AuthenticationManager authenticationManager = new AuthenticationManager() {
        @Override
        public String getAdminId() {
            return TEACHER_ADMIN.getId();
        }
    };
    protected static final AdminRefreshTokenRepositoryManager refreshTokenRepository = mock(AdminRefreshTokenRepositoryManager.class);
    protected static final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    protected static final Admin TEACHER_ADMIN = Admin.builder()
            .id("asdf1234")
            .password(passwordEncoder.encode("teacheradmin"))
            .name("본부교무실")
            .build();

    private static final List<Admin> ADMINS = new ArrayList<>();
    static {
        ADMINS.add(TEACHER_ADMIN);
    }

    protected static boolean addAccount(Admin admin) {
        boolean exists = ADMINS.stream().anyMatch(u -> u.getId().equals(admin.getId()));
        boolean requestCheck = admin.getId().length() > 8 || admin.getName().length() > 5;

        if (exists) return false;
        if(requestCheck) return false;

        ADMINS.add(admin);
        return true;
    }
}
