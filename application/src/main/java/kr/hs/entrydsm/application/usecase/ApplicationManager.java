package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.ApplicationFactory;
import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.integrate.user.ApplicantDocsService;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantStatusService;
import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.application.request.Information;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.request.GraduatedInformationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.response.GraduatedInformationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.response.InformationResponse;
import kr.hs.entrydsm.application.usecase.exception.*;
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
public class ApplicationManager implements ApplicationService {

    private final ApplicationFactory applicationFactory;
    private final ApplicantRepository applicantRepository;
    private final ImageService imageService;
    private final ApplicantDocsService applicantDocsService;
    private final ApplicationApplicantRepository applicationApplicantRepository;
    private final SchoolRepository schoolRepository;
    private final GraduationApplicationRepository graduationApplicationRepository;
    private final QualificationExamApplicationRepository qualificationExamApplicationRepository;
    private final ScoreCalculator scoreCalculator;
    private final ApplicantStatusService applicantStatusService;

    @Override
    public void writeSelfIntroduce(String content) {
        long receiptCode = AuthenticationManager.getUserReceiptCode();
        applicantDocsService.writeSelfIntroduce(receiptCode, content);
    }

    @Override
    public void writeStudyPlan(String content) {
        long receiptCode = AuthenticationManager.getUserReceiptCode();
        applicantDocsService.writeStudyPlan(receiptCode, content);
    }

    @Override
    public String getSelfIntroduce() {
        long receiptCode = AuthenticationManager.getUserReceiptCode();
        return applicantDocsService.getSelfIntroduce(receiptCode);
    }

    @Override
    public String getStudyPlan() {
        long receiptCode = AuthenticationManager.getUserReceiptCode();
        return applicantDocsService.getStudyPlan(receiptCode);
    }

    @Override
    public Page<School> getSchoolsByInformation(String information, Pageable pageable) {
        return schoolRepository.findByInformationContains(information, pageable);
    }

    @Override
    public void writeApplicationType(ApplicationRequest applicationRequest) {
        long receiptCode = AuthenticationManager.getUserReceiptCode();

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
        long receiptCode = AuthenticationManager.getUserReceiptCode();
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
        long receiptCode = AuthenticationManager.getUserReceiptCode();
        applicationApplicantRepository.writeInformation(receiptCode, information);
    }

    @Override
    public ApplicationResponse getApplicationType() {
        long receiptCode = AuthenticationManager.getUserReceiptCode();
        String educationStatus = applicationApplicantRepository.getEducationalStatus(receiptCode);
        if (educationStatus != null && !educationStatus.equals("QUALIFICATION_EXAM")) {
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
        long receiptCode = AuthenticationManager.getUserReceiptCode();
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
    public InformationResponse getInformation() {
        long receiptCode = AuthenticationManager.getUserReceiptCode();

        InformationResponse result = applicationApplicantRepository.getInformation(receiptCode);

        result.setPhotoFileName(getImageUrl(result.getPhotoFileName()));

        return result;
    }

    @Override
    public String uploadPhoto(MultipartFile multipartFile) {
        long receiptCode = AuthenticationManager.getUserReceiptCode();
        InformationResponse result = applicationApplicantRepository.getInformation(receiptCode);
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

    @Override
    public void finalSubmit() {
        Applicant applicant = applicantRepository.findByReceiptCode(AuthenticationManager.getUserReceiptCode());
        if (!checkType(applicant) || !checkInfo(applicant) || !checkScore(applicant) || !checkPhoto(applicant)) {
            throw new ProcessNotCompletedException();
        }
        applicantStatusService.finalSubmit(applicant.getReceiptCode());
    }

    private boolean checkType(Applicant applicant) {
        if (applicant.isQualificationExam()) {
            return qualificationExamApplicationRepository.findByReceiptCode(applicant.getReceiptCode())
                    .map(application -> (application.getQualifiedAt() != null) && isFilledType(applicant))
                    .orElse(false);
        }

        if (applicant.isGraduate()) {
            return graduationApplicationRepository.findByReceiptCode(applicant.getReceiptCode())
                    .map(application -> (application.getGraduatedAt() != null) && isFilledType(applicant))
                    .orElse(false);
        }

        return isFilledType(applicant);
    }

    private boolean isFilledType(Applicant applicant) {
        return !applicant.isEducationalStatusEmpty() && applicant.getApplicationType() != null
                && !isApplicationEmpty(applicant);
    }

    private boolean isApplicationEmpty(Applicant applicant) {
        if (applicant.isQualificationExam()) {
            return !qualificationExamApplicationRepository.existsByReceiptCode(applicant.getReceiptCode());
        } else if (applicant.isProspectiveGraduate() || applicant.isGraduate()) {
            return !graduationApplicationRepository.existsByReceiptCode(applicant.getReceiptCode());
        }
        return true;
    }

    private boolean checkInfo(Applicant applicant) {
        if (!checkType(applicant)) return false;

        if (applicant.isGraduate() || applicant.isProspectiveGraduate()) {
            GraduationApplication application = graduationApplicationRepository.findByReceiptCode(applicant.getReceiptCode())
                    .orElseThrow(ApplicationNotFoundException::new);
            return application.isFilledStudentInfo() && applicant.isFilledInfo();
        }

        return applicant.isFilledInfo();
    }

    private boolean checkScore(Applicant applicant) {
        if (!checkType(applicant)) return false;
        return scoreCalculator.isExists(applicant.getReceiptCode());
    }

    private boolean checkPhoto(Applicant applicant) {
		return applicant.getPhotoFileName() != null;
	}
	
    private String getImageUrl(String photoFileName) {
        try {
            return (photoFileName != null) ? imageService.generateObjectUrl(photoFileName) : null;
        } catch (MalformedURLException e){
            return null;
        }

    }

}
