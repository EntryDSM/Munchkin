package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.ApplicationFactory;
import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.integrate.user.ApplicantDocsService;
import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.application.Information;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.request.GraduatedInformationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.response.GraduatedInformationResponse;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.application.usecase.exception.EducationalStatusNotFoundException;
import kr.hs.entrydsm.application.usecase.exception.EducationalStatusUnmatchedException;
import kr.hs.entrydsm.application.usecase.exception.SchoolNotFoundException;
import kr.hs.entrydsm.application.usecase.image.ImageService;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ApplicationManager implements ApplicationProcessing {

    private final ApplicationFactory applicationFactory;
    private final ImageService imageService;
    private final ApplicantDocsService applicantDocsService;
    private final ApplicationApplicantRepository applicationApplicantRepository;
    private final SchoolRepository schoolRepository;
    private final GraduationApplicationRepository graduationApplicationRepository;
    private final QualificationExamApplicationRepository qualificationExamApplicationRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public void writeSelfIntroduce(String content) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        applicantDocsService.writeSelfIntroduce(receiptCode, content);
    }

    @Override
    public void writeStudyPlan(String content) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        applicantDocsService.writeStudyPlan(receiptCode, content);
    }

    @Override
    public String getSelfIntroduce() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        return applicantDocsService.getSelfIntroduce(receiptCode);
    }

    @Override
    public String getStudyPlan() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        return applicantDocsService.getStudyPlan(receiptCode);
    }

    @Override
    public Page<School> getSchoolsByInformation(String information, Pageable pageable) {
        return schoolRepository.findByInformationContains(information, pageable);
    }

    @Override
    public void writeApplicationType(ApplicationRequest applicationRequest) {
        long receiptCode = authenticationManager.getUserReceiptCode();

        if (applicationRequest.getEducationalStatus().equals("QUALIFICATION_EXAM")) {
            QualificationExamApplication qualificationExamApplication =
                    applicationFactory.createQualificationExamApplicationIfNotExists(receiptCode);
            qualificationExamApplication.setQualifiedAt(
                    YearMonth.parse(applicationRequest.getGraduatedAt(),
                            DateTimeFormatter.ofPattern("yyyyMM")
                                    .withZone(ZoneId.of("Asia/Seoul")))
                            .atDay(1));
            qualificationExamApplicationRepository.save(qualificationExamApplication);
        } else {
            GraduationApplication graduationApplication = applicationFactory.createGraduationApplicationIfNotExists(receiptCode);
            if (applicationRequest.getGraduatedAt() != null) {
                graduationApplication.setGraduatedAt(
                        YearMonth.parse(applicationRequest.getGraduatedAt(),
                                DateTimeFormatter.ofPattern("yyyyMM")
                                        .withZone(ZoneId.of("Asia/Seoul")))
                                .atDay(1));
            }
            graduationApplication.setIsGraduated(
                    applicationRequest.getEducationalStatus().equals("GRADUATE")
            );
            graduationApplicationRepository.save(graduationApplication);
        }

        applicationApplicantRepository.writeApplicationType(receiptCode, applicationRequest);
    }

    @Override
    public void writeGraduatedInformation(GraduatedInformationRequest information) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        String educationalStatus = applicationApplicantRepository.getEducationalStatus(receiptCode);

        if (educationalStatus == null)
            throw new EducationalStatusNotFoundException();
        if (educationalStatus.equals("QUALIFICATION_EXAM"))
            throw new EducationalStatusUnmatchedException();

        GraduationApplication graduationApplication = applicationFactory.createGraduationApplicationIfNotExists(receiptCode);

        graduationApplication.setSchoolTel(information.getSchoolTel());
        graduationApplication.setSchool(schoolRepository.findByCode(information.getSchoolCode())
                .orElseThrow(SchoolNotFoundException::new));
        graduationApplication.setStudentNumber(information.getStudentNumber());
        graduationApplicationRepository.save(graduationApplication);
    }

    @Override
    public void writeInformation(Information information) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        applicationApplicantRepository.writeInformation(receiptCode, information);
    }

    @Override
    public ApplicationResponse getApplicationType() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        String educationStatus = applicationApplicantRepository.getEducationalStatus(receiptCode);
        if (!educationStatus.equals("QUALIFICATION_EXAM")) {
            if (graduationApplicationRepository.findByReceiptCode(receiptCode).isPresent()) {
                GraduationApplication graduationApplication =
                        graduationApplicationRepository.findByReceiptCode(receiptCode)
                                .orElseThrow(ApplicationNotFoundException::new);

                if (graduationApplication.getGraduatedAt() != null)
                    return applicationApplicantRepository.getApplicationType(receiptCode)
                            .setGraduatedAt(DateTimeFormatter.ofPattern("yyyyMM")
                                    .withZone(ZoneId.of("Asia/Seoul"))
                                    .format(graduationApplication.getGraduatedAt()))
                            .setIsGraduated(graduationApplication.getIsGraduated());
                return applicationApplicantRepository.getApplicationType(receiptCode)
                        .setIsGraduated(graduationApplication.getIsGraduated() != null && graduationApplication.getIsGraduated());
            }
        } else {
            if (qualificationExamApplicationRepository.findByReceiptCode(receiptCode).isPresent()) {
                QualificationExamApplication qualificationExamApplication =
                        qualificationExamApplicationRepository.findByReceiptCode(receiptCode)
                                .orElseThrow(ApplicationNotFoundException::new);
                if (qualificationExamApplication.getQualifiedAt() != null)
                    return applicationApplicantRepository.getApplicationType(receiptCode)
                            .setGraduatedAt(DateTimeFormatter.ofPattern("yyyyMM")
                                    .withZone(ZoneId.of("Asia/Seoul"))
                                    .format(qualificationExamApplication.getQualifiedAt()));
                return applicationApplicantRepository.getApplicationType(receiptCode);
            }
        }
        return applicationApplicantRepository.getApplicationType(receiptCode);
    }

    @Override
    public GraduatedInformationResponse getGraduatedInformation() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        String educationalStatus = applicationApplicantRepository.getEducationalStatus(receiptCode);

        if (educationalStatus == null)
            throw new EducationalStatusNotFoundException();
        if (educationalStatus.equals("QUALIFICATION_EXAM"))
            throw new EducationalStatusUnmatchedException();

        GraduatedInformationResponse result = new GraduatedInformationResponse()
                .setInformation(applicationApplicantRepository.getInformation(receiptCode));

        if (graduationApplicationRepository.findByReceiptCode(receiptCode).isPresent()) {
            GraduationApplication graduationApplication = graduationApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);

            result.setSchoolCode(graduationApplication.getSchoolCode());
            if(graduationApplication.getSchoolCode() != null)
                result.setSchoolName(
                    schoolRepository.findByCode(graduationApplication.getSchoolCode())
                    .orElseThrow(SchoolNotFoundException::new).getName()
                );
            else result.setSchoolName(null);
            result.setSchoolTel(graduationApplication.getSchoolTel());
            result.setIsGraduated(graduationApplication.getIsGraduated() != null
                    && graduationApplication.getIsGraduated());
            result.setStudentNumber(graduationApplication.getStudentNumber());
        }
        result.setPhotoFileName(getImageUrl(result.getPhotoFileName()));

        return result;
    }

    @Override
    public Information getInformation() {
        long receiptCode = authenticationManager.getUserReceiptCode();

        Information result = applicationApplicantRepository.getInformation(receiptCode);

        result.setPhotoFileName(getImageUrl(result.getPhotoFileName()));

        return result;
    }

    @Override
    public String uploadPhoto(MultipartFile multipartFile) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        Information result = applicationApplicantRepository.getInformation(receiptCode);
        if(result.getPhotoFileName() != null)
            imageService.delete(result.getPhotoFileName());
        String fileName;
        try {
            fileName = imageService.upload(multipartFile, receiptCode);
        } catch (IOException e) {
            fileName = null;
        }

        applicationApplicantRepository.setPhotoFileName(receiptCode, fileName);
        return fileName;
    }

//    @Override
//    public void updateSubjectScore(SubjectScoreRequest score) {
//        scoreService.updateSubjectScore(score);
//    }
//
//    @Override
//    public void updateEtcScore(EtcScoreRequest score) {
//        scoreService.updateEtcScore(score);
//    }
//
//    @Override
//    public void updateGedScore(GedScoreRequest score) {
//        scoreService.updateGedScore(score);
//    }
//
//    @Override
//    public SubjectScoreResponse getSubjectScore() {
//        long receiptCode = authenticationManager.getUserReceiptCode();
//        String educationalStatus = applicantExportService.getEducationalStatus(receiptCode);
//
//        if(educationalStatus == null)
//            throw new EducationalStatusNotFoundException();
//        if(educationalStatus.equals("QUALIFICATION_EXAM"))
//            throw new EducationalStatusUnmatchedException();
//        return scoreService.getSubjectScore();
//    }
//
//    @Override
//    public EtcScoreResponse getEtcScore() {
//        long receiptCode = authenticationManager.getUserReceiptCode();
//        String educationalStatus = applicantExportService.getEducationalStatus(receiptCode);
//
//        if(educationalStatus == null)
//            throw new EducationalStatusNotFoundException();
//        if(educationalStatus.equals("QUALIFICATION_EXAM"))
//            throw new EducationalStatusUnmatchedException();
//        return scoreService.getEtcScore();
//    }
//
//    @Override
//    public GedScoreResponse getGedScore() {
//        long receiptCode = authenticationManager.getUserReceiptCode();
//        String educationalStatus = applicantExportService.getEducationalStatus(receiptCode);
//
//        if(educationalStatus == null)
//            throw new EducationalStatusNotFoundException();
//        if(!educationalStatus.equals("QUALIFICATION_EXAM"))
//            throw new EducationalStatusUnmatchedException();
//
//        return scoreService.getGedScore();
//    }
//
//    @Override
//    public TotalScoreResponse getScore() {
//        long receiptCode = authenticationManager.getUserReceiptCode();
//        String educationalStatus = applicantExportService.getEducationalStatus(receiptCode);
//
//        if(educationalStatus == null)
//            throw new EducationalStatusNotFoundException();
//        if(educationalStatus.equals("QUALIFICATION_EXAM"))
//            throw new EducationalStatusUnmatchedException();
//        return scoreService.getScore();
//    }
//
//
    private String getImageUrl(String photoFileName) {
        try {
            return (photoFileName != null) ? imageService.generateObjectUrl(photoFileName) : null;
        } catch (MalformedURLException e){
            return null;
        }

    }

}
