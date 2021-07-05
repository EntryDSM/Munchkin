package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.ApplicationFactory;
import kr.hs.entrydsm.application.builder.GraduationApplicationBuilder;
import kr.hs.entrydsm.application.builder.QualificationExamApplicationBuilder;
import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.integrate.user.ApplicantDocsService;
import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.application.Information;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.request.GraduatedInformationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;
import kr.hs.entrydsm.application.usecase.exception.EducationalStatusNotFoundException;
import kr.hs.entrydsm.application.usecase.exception.EducationalStatusUnmatchedException;
import kr.hs.entrydsm.application.usecase.exception.SchoolNotFoundException;
import kr.hs.entrydsm.application.usecase.image.ImageService;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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
    public void writeGraduateApplicationType() {
        ApplicationRequest request =
                new ApplicationRequest("GRADUATE", "MEISTER",
                        false, null,
                        "202003");
        Mockito.when(graduationApplicationRepository.existsByReceiptCode(0L))
                .thenReturn(true);
        Mockito.when(graduationApplicationRepository.findByReceiptCode(0L))
                .thenReturn(
                        Optional.of(
                                GraduationApplication.builder()
                                        .receiptCode(0L)
                                        .build()
                        )
                );

        applicationProcessing.writeApplicationType(request);

    }

    @Test
    public void writeQualificationApplicationType() {
        ApplicationRequest request =
                new ApplicationRequest("QUALIFICATION_EXAM", "MEISTER",
                        false, null,
                        "202003");

        Mockito.when(qualificationExamApplicationRepository.existsByReceiptCode(0L))
                .thenReturn(true);

        Mockito.when(qualificationExamApplicationRepository.findByReceiptCode(0L))
                .thenReturn(
                        Optional.of(
                                QualificationExamApplication.builder()
                                        .receiptCode(0L)
                                        .build()
                        )
                );

        applicationProcessing.writeApplicationType(request);
    }

    @Test
    public void writeGraduatedInformation() {
        GraduatedInformationRequest request =
                new GraduatedInformationRequest("01012345678",
                        "1111111", "1234");

        Mockito.when(applicationApplicantRepository
                .getEducationalStatus(0L))
                .thenReturn("GRADUTE");

        Mockito.when(schoolRepository
                .findByCode(request.getSchoolCode()))
                .thenReturn(Optional.of(School.builder().build()));

        applicationProcessing.writeGraduatedInformation(request);

    }

    @Test
    public void invalidEducationStatusQualification() {

        GraduatedInformationRequest request =
                new GraduatedInformationRequest("01012345678",
                        "1111111", "1234");

        Mockito.when(applicationApplicantRepository
                .getEducationalStatus(0L))
                .thenReturn("QUALIFICATION_EXAM");

        assertThrows(EducationalStatusUnmatchedException.class, () -> {
            applicationProcessing.writeGraduatedInformation(request);
        });
    }

    @Test
    public void invalidSchoolCode() {
        GraduatedInformationRequest request =
                new GraduatedInformationRequest("01012345678",
                        "1111111", "1234");

        Mockito.when(applicationApplicantRepository
                .getEducationalStatus(0L))
                .thenReturn("GRADUTE");

        Mockito.when(schoolRepository
                .findByCode(request.getSchoolCode()))
                .thenReturn(Optional.empty());

        assertThrows(SchoolNotFoundException.class, () -> {
            applicationProcessing.writeGraduatedInformation(request);
        });
    }

    @Test
    void educationalStatusisNull() throws IllegalAccessException {
        GraduatedInformationRequest request =
                new GraduatedInformationRequest("01012345678",
                        "1111111", "1234");

        Mockito.when(applicationApplicantRepository
                .getEducationalStatus(0L))
                .thenReturn(null);

        assertThrows(EducationalStatusNotFoundException.class, () -> {
            applicationProcessing.writeGraduatedInformation(request);
        });
    }

    @Test
    void writeInformation() {
        Information information =
                Information.builder()
                        .name("test1")
                        .sex("MALE")
                        .birthday("20040728")
                        .parentName("test1parent")
                        .telephoneNumber("01012345678")
                        .parentTel("01087654321")
                        .homeTel("0510231564")
                        .address("homeaddr")
                        .detailAddress("thisismyhome")
                        .postCode("12345")
                        .photoFileName("test.jpg")
                        .build();

        applicationProcessing.writeInformation(information);

    }

    @Test
    void getGraduateApplicationType() {

        Mockito.when(applicationApplicantRepository
                .getEducationalStatus(0L))
                .thenReturn("GRADUTE");

        Mockito.when(graduationApplicationRepository
                .findByReceiptCode(0L))
                .thenReturn(Optional.of(
                        GraduationApplicationBuilder.build(0L)
                ));

        Mockito.when(applicationApplicantRepository
                .getApplicationType(0L))
                .thenReturn(
                        ApplicationResponse.builder().build()
                );

        applicationProcessing.getApplicationType();

    }

    @Test
    void getQualificationApplicationType() {
        Mockito.when(applicationApplicantRepository
                .getEducationalStatus(0L))
                .thenReturn("QUALIFICATION_EXAM");

        Mockito.when(qualificationExamApplicationRepository
                .findByReceiptCode(0L))
                .thenReturn(Optional.of(
                        QualificationExamApplicationBuilder.build(0L)
                ));

        Mockito.when(applicationApplicantRepository
                .getApplicationType(0L))
                .thenReturn(
                        ApplicationResponse.builder().build()
                );

        applicationProcessing.getApplicationType();
    }

    @Test
    void getGraduatedInformation() {

        Mockito.when(applicationApplicantRepository
                .getEducationalStatus(0L))
                .thenReturn("GRADUTE");

        Mockito.when(graduationApplicationRepository
                .findByReceiptCode(0L))
                .thenReturn(Optional.of(
                        GraduationApplicationBuilder.build(0L)
                ));

        Mockito.when(schoolRepository
                .findByCode("33333"))
                .thenReturn(Optional.of(School.builder().build()));

        Mockito.when(applicationApplicantRepository
                .getInformation(0L))
                .thenReturn(
                                Information.builder()
                                        .name("test1")
                                        .sex("MALE")
                                        .birthday("20040728")
                                        .parentName("test1parent")
                                        .telephoneNumber("01012345678")
                                        .parentTel("01087654321")
                                        .homeTel("0510231564")
                                        .address("homeaddr")
                                        .detailAddress("thisismyhome")
                                        .postCode("12345")
                                        .photoFileName("test.jpg")
                                        .build()
                );

        applicationProcessing.getGraduatedInformation();



    }

    @Test
    void getInformation() {

        Mockito.when(applicationApplicantRepository
                .getInformation(0L))
                .thenReturn(
                        Information.builder()
                                .name("test1")
                                .sex("MALE")
                                .birthday("20040728")
                                .parentName("test1parent")
                                .telephoneNumber("01012345678")
                                .parentTel("01087654321")
                                .homeTel("0510231564")
                                .address("homeaddr")
                                .detailAddress("thisismyhome")
                                .postCode("12345")
                                .photoFileName("test.jpg")
                                .build()
                );

        applicationProcessing.getInformation();

    }

    @Test
    void uploadPhoto() {

        Mockito.when(applicationApplicantRepository
                .getInformation(0L))
                .thenReturn(
                        Information.builder()
                                .name("test1")
                                .sex("MALE")
                                .birthday("20040728")
                                .parentName("test1parent")
                                .telephoneNumber("01012345678")
                                .parentTel("01087654321")
                                .homeTel("0510231564")
                                .address("homeaddr")
                                .detailAddress("thisismyhome")
                                .postCode("12345")
                                .photoFileName("test.jpg")
                                .build()
                );

        applicationProcessing.uploadPhoto(new MockMultipartFile("test.png", new byte[0]));
    }
}