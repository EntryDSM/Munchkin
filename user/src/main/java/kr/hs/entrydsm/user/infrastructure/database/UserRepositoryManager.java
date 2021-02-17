package kr.hs.entrydsm.user.infrastructure.database;

import kr.hs.entrydsm.user.domain.entity.User;
import kr.hs.entrydsm.user.domain.repository.UserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryManager extends CrudRepository<User, Long>, UserRepository {
}
