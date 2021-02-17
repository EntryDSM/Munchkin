package kr.hs.entrydsm.admin.infrastructure.database;

import kr.hs.entrydsm.admin.domain.entity.Admin;
import kr.hs.entrydsm.admin.domain.repository.AdminRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepositoryManager extends CrudRepository<Admin, String>, AdminRepository {

    @Override
    Optional<Admin> findById(String id);

}
