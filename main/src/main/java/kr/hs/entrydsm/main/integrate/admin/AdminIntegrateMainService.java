package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.infrastructure.database.AdminRepositoryManager;
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

@RequiredArgsConstructor
@Component
public class AdminIntegrateMainService implements MainRepository {

    //user domain
    private final UserRepositoryManager userRepositoryManager;
    private final StatusRepositoryManager statusRepositoryManager;

    //admin domain
    private final AdminRepositoryManager adminRepositoryManager;
    private final ScheduleRepositoryManager scheduleRepositoryManager;

    //score domain
    private final ScoreRepositoryManager scoreRepositoryManager;

    //application domain
    private final ApplicationRepositoryManager applicationRepositoryManager;
    private final GraduationApplicationRepositoryManager graduationApplicationRepositoryManager;
    private final QualificationExamApplicationRepositoryManager qualificationExamApplicationRepositoryManager;
    private final SchoolRepositoryManager schoolRepositoryManager;


    @Override
    public void deleteAll() {
        userRepositoryManager.deleteAll();
        statusRepositoryManager.deleteAll();

        adminRepositoryManager.deleteAll();
        scheduleRepositoryManager.deleteAll();

        scoreRepositoryManager.deleteAll();

        applicationRepositoryManager.deleteAll();
        graduationApplicationRepositoryManager.deleteAll();
        qualificationExamApplicationRepositoryManager.deleteAll();
        schoolRepositoryManager.deleteAll();
    }

}