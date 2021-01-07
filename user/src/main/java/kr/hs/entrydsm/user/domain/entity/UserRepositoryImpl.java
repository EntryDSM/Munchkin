package kr.hs.entrydsm.user.domain.entity;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static int INDEX = 0;
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public Optional<User> findByReceiptCode(Integer receiptCode) {
        return Optional.ofNullable(users.get(receiptCode));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User save(User user) {
        return users.put(INDEX++, user);
    }

}
