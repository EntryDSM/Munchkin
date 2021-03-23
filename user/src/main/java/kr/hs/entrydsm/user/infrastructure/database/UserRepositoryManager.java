package kr.hs.entrydsm.user.infrastructure.database;

import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.UserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryManager extends CrudRepository<User, Long>, UserRepository {
}
