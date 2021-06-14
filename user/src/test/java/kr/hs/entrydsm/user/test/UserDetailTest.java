package kr.hs.entrydsm.user.test;

import kr.hs.entrydsm.user.UserBaseTest;
import kr.hs.entrydsm.user.entity.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("유저 상세정보 테스트")
public class UserDetailTest extends UserBaseTest {

    @Test
    public void 유저_추가_성공() {
        assertTrue(addUser(
                User.builder()
                        .email("test2@test.com")
                        .name("테스트2")
                        .password("testPassword")
                        .selfIntroduce("자기소개")
                        .studyPlan("학업계획")
                        .build()
        ));
    }

    @Test
    public void 유저_추가_실패() {
        assertFalse(addUser(
                User.builder()
                        .email("test@test.com")
                        .name("테스트")
                        .password("testPassword")
                        .selfIntroduce("자기소개")
                        .studyPlan("학업계획")
                        .build()
        ));
    }

    @Test
    public void 유저_조회_성공() {
        assertEquals(findUser(1).getEmail(), "test@test.com");
    }

    @Test
    public void 유저_조회_실패() {
        assertNull(findUser(0));
    }

    @Test
    public void 유저상태_조회() {
        assertFalse(TEST_USER.getStatus().isSubmit());
        assertFalse(TEST_USER.getStatus().isPrintedArrived());
    }

}
