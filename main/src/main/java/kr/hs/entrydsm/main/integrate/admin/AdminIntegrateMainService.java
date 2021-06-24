package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.infrastructure.database.AdminRefreshTokenRepositoryManager;
import kr.hs.entrydsm.admin.infrastructure.database.AdminRepositoryManager;
import kr.hs.entrydsm.admin.infrastructure.database.ScheduleRepositoryManager;
import kr.hs.entrydsm.admin.integrate.main.MainRepository;
import kr.hs.entrydsm.application.infrastructure.database.ApplicationRepositoryManager;
import kr.hs.entrydsm.application.infrastructure.database.GraduationApplicationRepositoryManager;
import kr.hs.entrydsm.application.infrastructure.database.QualificationExamApplicationRepositoryManager;
import kr.hs.entrydsm.application.infrastructure.database.SchoolRepositoryManager;
import kr.hs.entrydsm.score.infrastructure.database.ScoreRepositoryManager;
import kr.hs.entrydsm.user.infrastructure.database.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Lazy
@Component
public class AdminIntegrateMainService implements MainRepository {

    //user domain
    private final UserRepositoryManager userRepositoryManager;
    private final StatusRepositoryManager statusRepositoryManager;
    private final AuthCodeLimitRedisRepositoryManager authCodeLimitRedisRepositoryManager;
    private final AuthCodeRedisRepositoryManager authCodeRedisRepositoryManager;
    private final RefreshTokenRepositoryManager userRefreshTokenRepositoryManager;

    //admin domain
    private final AdminRepositoryManager adminRepositoryManager;
    private final ScheduleRepositoryManager scheduleRepositoryManager;
    private final AdminRefreshTokenRepositoryManager adminRefreshTokenRepositoryManager;

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
        authCodeLimitRedisRepositoryManager.deleteAll();
        authCodeRedisRepositoryManager.deleteAll();
        userRefreshTokenRepositoryManager.deleteAll();

        adminRepositoryManager.deleteAll();
        scheduleRepositoryManager.deleteAll();
        adminRefreshTokenRepositoryManager.deleteAll();

        scoreRepositoryManager.deleteAll();

        applicationRepositoryManager.deleteAll();
        graduationApplicationRepositoryManager.deleteAll();
        qualificationExamApplicationRepositoryManager.deleteAll();
        schoolRepositoryManager.deleteAll();

    }

}
