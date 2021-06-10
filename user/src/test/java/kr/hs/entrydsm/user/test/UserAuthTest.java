package kr.hs.entrydsm.user.test;

import kr.hs.entrydsm.user.UserBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("유저 인증 테스트")
public class UserAuthTest extends UserBaseTest {

    @Test
    public void 유저_인증_성공() {
        assertEquals(TEST_USER.getEmail(), "test@test.com");
        assertEquals(TEST_USER.getPassword(), "testPassword");
    }

    @Test
    public void 유저_인증_실패() {
        assertNotEquals(TEST_USER.getEmail(), "asd@test.com");
        assertNotEquals(TEST_USER.getPassword(), "testPassword2");
    }

}
