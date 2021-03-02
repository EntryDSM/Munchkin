package kr.hs.entrydsm.application.infrastructure.database;

import kr.hs.entrydsm.application.entity.School;
import kr.hs.entrydsm.application.entity.SchoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepositoryManager extends CrudRepository<School, String>, SchoolRepository {

    @Override
    Page<School> findByInformationContains(String information, Pageable pageable);

    @Override
    Optional<School> findByCode(String code);
}
