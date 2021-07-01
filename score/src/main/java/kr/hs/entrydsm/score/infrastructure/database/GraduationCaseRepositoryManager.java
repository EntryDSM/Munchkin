package kr.hs.entrydsm.score.infrastructure.database;

import kr.hs.entrydsm.score.entity.GraduationCase;
import kr.hs.entrydsm.score.entity.GraduationCaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduationCaseRepositoryManager extends CrudRepository<GraduationCase, Long>, GraduationCaseRepository {
}
