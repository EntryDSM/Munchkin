package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.usecase.auth.AuthService;
import kr.hs.entrydsm.admin.usecase.auth.AuthServiceManager;
import kr.hs.entrydsm.admin.usecase.dto.request.SignInRequest;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.PasswordNotValidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("어드민 계정 테스트")
public class AdminAccountTest extends AdminBaseTest {

    private static final AuthService authService = new AuthServiceManager(adminRepository, passwordEncoder,
            authenticationManager, refreshTokenRepository, jwtTokenProvider);

    @Test
    public void 어드민_선생님_계정_추가() {
        assertTrue(addAccount(
                Admin.builder()
                        .id("test1234")
                        .name("교무실")
                        .password("testPassword")
                        .build()
                )
        );
    }

    @Test
    public void 어드민_선생님_계정_추가_실패() {
        assertFalse(addAccount(
                Admin.builder()
                        .id("test2@test.com")
                        .name("교무실")
                        .password("testPassword")
                        .build()
                )
        );
    }

    @Test
    public void 어드민_행정실_계정_추가() {
        assertTrue(addAccount(
                Admin.builder()
                        .id("test4567")
                        .name("행정실")
                        .password("testPassword")
                        .build()
                )
        );
    }

    @Test
    public void 어드민_교무실_로그인() {
        assertEquals(TEACHER_ADMIN, "asdf4567");
        assertEquals(TEACHER_ADMIN.getPassword(), passwordEncoder.encode("officeadmin"));
        try {
            authService.login(new SignInRequest("asdf4567", "officeadmin"));
        } catch (AdminNotFoundException e) {
        }
    }

    @Test
    public void 어드민_교무실_비밀번호_확인() {
        assertEquals(TEACHER_ADMIN.getId(), "asdf1234");
        try {
            authService.checkPassword("teacheradmin");
        } catch(PasswordNotValidException e) {
        }
    }

}
