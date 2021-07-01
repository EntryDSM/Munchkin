package kr.hs.entrydsm.score.infrastructure.database;

import kr.hs.entrydsm.score.entity.QualificationExamCase;
import kr.hs.entrydsm.score.entity.QualificationExamCaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationExamCaseRepositoryManager extends CrudRepository<QualificationExamCase, Long>, QualificationExamCaseRepository {
}
