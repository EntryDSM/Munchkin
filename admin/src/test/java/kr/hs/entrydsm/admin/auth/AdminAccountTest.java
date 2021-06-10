package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.entity.admin.Permission;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("어드민 계정 테스트")
public class AdminAccountTest extends AdminBaseTest {

    @Test
    public void 어드민_선생님_계정_추가() {
        assertTrue(addAccount(
                Admin.builder()
                        .id("test1234")
                        .name("교무실")
                        .password("testPassword")
                        .permission(Permission.TEACHER)
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
                        .permission(Permission.TEACHER)
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
                        .permission(Permission.OFFICE)
                        .build()
                )
        );
    }

    @Test
    public void 어드민_행정실_계정_추가_실패() {
        assertFalse(addAccount(
                Admin.builder()
                        .id("test4567890")
                        .name("행정실")
                        .password("testPassword")
                        .permission(Permission.OFFICE)
                        .build()
                )
        );
    }

    @Test
    public void 어드민_교무실_로그인() {
        assertEquals(TEACHER_ADMIN.getId(), "asdf1234");
        assertEquals(TEACHER_ADMIN.getPassword(), "teacheradmin");
    }

    @Test
    public void 어드민_교무실_로그인_실패() {
        assertNotEquals(TEACHER_ADMIN.getId(), "asdf12345");
        assertNotEquals(TEACHER_ADMIN.getPassword(), "teachderadmin");
    }

    @Test
    public void 어드민_행정실_로그인() {
        assertEquals(OFFICE_ADMIN.getId(), "asdf4567");
        assertEquals(OFFICE_ADMIN.getPassword(), "officeadmin");
    }

    @Test
    public void 어드민_행정실_로그인_실패() {
        assertNotEquals(OFFICE_ADMIN.getId(), "asdf4568");
        assertNotEquals(OFFICE_ADMIN.getPassword(), "offdiceadmin");
    }

}
