package kr.hs.entrydsm.application.infrastructure.database;

import kr.hs.entrydsm.application.domain.entity.School;
import kr.hs.entrydsm.application.domain.repository.SchoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepositoryManager extends CrudRepository<School, String>, SchoolRepository {

    @Override
    Page<School> findByInformationContains(String information, Pageable pageable);
}