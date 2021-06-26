package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.usecase.auth.AuthService;
import kr.hs.entrydsm.admin.usecase.dto.request.SignInRequest;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.InvalidTokenException;
import kr.hs.entrydsm.admin.usecase.exception.PasswordNotValidException;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("admin-auth")
@SpringBootTest(classes = AuthenticationManager.class)
public class AdminAccountTest extends AdminBaseTest {

    @MockBean
    AuthService authService;

    @Test
    public void add_account() {
        assertTrue(addAccount(
                Admin.builder()
                        .id("test1234")
                        .name("tcher")
                        .password("testPassword")
                        .build()
                )
        );
    }

    @Test
    public void login() {
        assertEquals(TEACHER_ADMIN.getId(), "asdf1234");
        assertEquals(TEACHER_ADMIN.getPassword(), passwordEncoder.encode("teacheradmin"));
        authService.login(new SignInRequest("asdf1234", "teacheradmin"));
    }

    @Test
    public void login_fail() {
        assertEquals(TEACHER_ADMIN.getId(), "asdf1234");
        assertEquals(TEACHER_ADMIN.getPassword(), passwordEncoder.encode("teacheradmin"));
        when(authService.login(new SignInRequest("asdf123", "teacheradmin")))
                .thenThrow(AdminNotFoundException.class);
    }

    @Test
    public void checking_password() {
        assertEquals(TEACHER_ADMIN.getId(), "asdf1234");
        authService.checkPassword("teacheradmin");
    }

    @Test
    public void checking_password_fail() {
        assertEquals(TEACHER_ADMIN.getId(), "asdf1234");
        when(authService.checkPassword("teacheradmn"))
                .thenThrow(PasswordNotValidException.class);
    }

    @Test
    public void refresh_token() {
        when(authService.tokenRefresh("asdf.asdf.asdf"))
                .thenThrow(InvalidTokenException.class);
    }

    @Test
    public void check_refresh_token() {
        assertEquals(REFRESH_TOKEN.getId(), TEACHER_ADMIN.getId());
        assertEquals(REFRESH_TOKEN.getRefreshExp(), 123456L);
        assertFalse(REFRESH_TOKEN.getRefreshToken().equals("asdf.asdf.asdf"));
    }

}
