package kr.hs.entrydsm.user;

import kr.hs.entrydsm.user.entity.status.Status;
import kr.hs.entrydsm.user.entity.user.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UserBaseTest {

    private static final Status TEST_USER_STATUS = Status.builder()
            .isPrintedArrived(false)
            .isSubmit(false)
            .build();

    protected static final User TEST_USER = User.builder()
            .email("test@test.com")
            .name("테스트")
            .password("testPassword")
            .selfIntroduce("자기소개")
            .studyPlan("학업계획")
            .status(TEST_USER_STATUS)
            .build();

    private static int index = 1;
    private static final List<User> USERS = new ArrayList<>();
    static {
        addUser(TEST_USER);
    }

    protected static boolean addUser(User user) {
        boolean exists = USERS.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()));
        if (exists) return false;

        try {
            Field field = User.class.getDeclaredField("receiptCode");
            field.setAccessible(true);
            field.set(user, index++);

            USERS.add(user);
            return true;
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            return false;
        }
    }

    protected static User findUser(long receiptCode) {
        return USERS.stream()
                .filter(user -> user.getReceiptCode() == receiptCode)
                .findFirst()
                .orElse(null);
    }

}
