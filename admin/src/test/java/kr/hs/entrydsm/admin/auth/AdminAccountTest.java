package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.usecase.auth.AuthService;
import kr.hs.entrydsm.admin.usecase.auth.AuthServiceManager;
import kr.hs.entrydsm.admin.usecase.dto.request.SignInRequest;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.PasswordNotValidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("admin-auth")
@SpringBootTest(classes = AuthServiceManager.class)
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
        try {
            authService.login(new SignInRequest("asdf1234", "teacheradmin"));
        } catch (AdminNotFoundException e) {
        }
    }

    @Test
    public void checking_password() {
        assertEquals(TEACHER_ADMIN.getId(), "asdf1234");
        try {
            authService.checkPassword("teacheradmin");
        } catch(PasswordNotValidException e) {
        }
    }

}
