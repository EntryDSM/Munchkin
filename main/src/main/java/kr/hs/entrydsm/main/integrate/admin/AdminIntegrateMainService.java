package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.infrastructure.database.ScheduleRepositoryManager;
import kr.hs.entrydsm.admin.integrate.main.MainRepository;
import kr.hs.entrydsm.application.infrastructure.database.ApplicationRepositoryManager;
import kr.hs.entrydsm.application.infrastructure.database.GraduationApplicationRepositoryManager;
import kr.hs.entrydsm.application.infrastructure.database.QualificationExamApplicationRepositoryManager;
import kr.hs.entrydsm.application.infrastructure.database.SchoolRepositoryManager;
import kr.hs.entrydsm.score.infrastructure.database.ScoreRepositoryManager;
import kr.hs.entrydsm.user.infrastructure.database.StatusRepositoryManager;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class AdminIntegrateMainService implements MainRepository {

    private final UserRepositoryManager userRepositoryManager;
    private final StatusRepositoryManager statusRepositoryManager;

    private final ScheduleRepositoryManager scheduleRepositoryManager;

    private final ScoreRepositoryManager scoreRepositoryManager;

    private final ApplicationRepositoryManager applicationRepositoryManager;

    private final GraduationApplicationRepositoryManager graduationApplicationRepositoryManager;
    private final QualificationExamApplicationRepositoryManager qualificationExamApplicationRepositoryManager;
    private final SchoolRepositoryManager schoolRepositoryManager;


    @Override
    @Transactional
    public void deleteAll() {
        scheduleRepositoryManager.deleteAll();

        scoreRepositoryManager.deleteAll();

        applicationRepositoryManager.deleteAll();

        qualificationExamApplicationRepositoryManager.deleteAll();
        graduationApplicationRepositoryManager.deleteAll();

        schoolRepositoryManager.deleteAll();

        statusRepositoryManager.deleteAll();
        userRepositoryManager.deleteAll();
    }

}
