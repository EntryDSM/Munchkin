package kr.hs.entrydsm.application.infrastructure.database;

import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplicationRepository;
import org.springframework.data.repository.CrudRepository;

public interface QualificationExamApplicationRepositoryManager extends QualificationExamApplicationRepository, CrudRepository<QualificationExamApplication, Long> {
}
