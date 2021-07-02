package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.ApplicationFactory;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.GraduationApplicationRepository;
import kr.hs.entrydsm.application.entity.QualificationExamApplicationRepository;
import kr.hs.entrydsm.application.entity.SchoolRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantDocsService;
import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.image.ImageService;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationManagerTest {

    private ApplicationApplicantRepository applicationApplicantRepository =
            Mockito.mock(ApplicationApplicantRepository.class);

    private SchoolRepository schoolRepository =
            Mockito.mock(SchoolRepository.class);

    private GraduationApplicationRepository graduationApplicationRepository =
            Mockito.mock(GraduationApplicationRepository.class);

    private QualificationExamApplicationRepository qualificationExamApplicationRepository =
            Mockito.mock(QualificationExamApplicationRepository.class);

    private AuthenticationManager authenticationManager =
            Mockito.mock(AuthenticationManager.class);

    private ImageService imageService =
            Mockito.mock(ImageService.class);

    private ApplicantDocsService applicantDocsService =
            Mockito.mock(ApplicantDocsService.class);

    private ApplicationFactory applicationFactory =
            new ApplicationFactory(graduationApplicationRepository,
                    qualificationExamApplicationRepository);

    private ApplicationProcessing applicationProcessing =
            new ApplicationManager(applicationFactory, imageService,
                    applicantDocsService, applicationApplicantRepository,
                    schoolRepository, graduationApplicationRepository,
                    qualificationExamApplicationRepository, authenticationManager
                    );


    @Test
    void writeSelfIntroduce() {
    }

    @Test
    void writeStudyPlan() {
    }

    @Test
    void getSelfIntroduce() {
    }

    @Test
    void getStudyPlan() {
    }

    @Test
    void getSchoolsByInformation() {
    }

    @Test
    void writeApplicationType() {
        ApplicationRequest request =
                new ApplicationRequest("GRADUATE", "MEISTER",
                        false, null,
                        "202003");
        Mockito.when(graduationApplicationRepository.existsByReceiptCode(1L))
                .thenReturn(true);
        Mockito.when(graduationApplicationRepository.findByReceiptCode(1L))
                .thenReturn(
                        Optional.of(
                                GraduationApplication.builder()
                                        .receiptCode(1L)
                                        .build()
                        )
                );
        
        applicationProcessing.writeApplicationType(request);

    }

    @Test
    void writeGraduatedInformation() {
    }

    @Test
    void writeInformation() {
    }

    @Test
    void getApplicationType() {
    }

    @Test
    void getGraduatedInformation() {
    }

    @Test
    void getInformation() {
    }

    @Test
    void uploadPhoto() {
    }
}