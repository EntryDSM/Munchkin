package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.integrate.user.ApplicantDocsService;
import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.request.InformationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.response.InformationResponse;
import kr.hs.entrydsm.application.usecase.dto.score.request.EtcScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.request.GedScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.request.SubjectScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.response.EtcScoreResponse;
import kr.hs.entrydsm.application.usecase.dto.score.response.GedScoreResponse;
import kr.hs.entrydsm.application.usecase.dto.score.response.SubjectScoreResponse;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.application.usecase.exception.SchoolNotFoundException;
import kr.hs.entrydsm.application.usecase.image.ImageService;
import kr.hs.entrydsm.application.usecase.score.ScoreService;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ApplicationManager implements ApplicationProcessing {

    private final ImageService imageService;
    private final ApplicantDocsService applicantDocsService;
    private final ApplicationApplicantRepository applicantExportService;
    private final SchoolRepository schoolRepository;
    private final GraduationApplicationRepository graduationApplicationRepository;
    private final ScoreService scoreService;
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
        GraduationApplication graduationApplication = getGraduationApplication(receiptCode);
        if(applicationRequest.getGraduatedAt() != null){
            graduationApplication.setReceiptCode(receiptCode);
            graduationApplication.setGraduateAt(
                    YearMonth.parse(applicationRequest.getGraduatedAt(),
                            DateTimeFormatter.ofPattern("yyyyMM")).atDay(2));
            graduationApplicationRepository.save(graduationApplication);
        }
        applicantExportService.writeApplicationType(receiptCode, applicationRequest);
    }

    @Override
    public void writeInformation(InformationRequest information) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = getGraduationApplication(receiptCode);

        graduationApplication.setSchoolTel(information.getSchoolTel());
        graduationApplication.setSchool(schoolRepository.findByCode(information.getSchoolCode())
                .orElseThrow(SchoolNotFoundException::new));
        graduationApplication.setStudentNumber(information.getStudentNumber());
        graduationApplication.setIsGraduated(information.isGraduated());
        graduationApplicationRepository.save(graduationApplication);

        applicantExportService.writeInformation(receiptCode, information);
    }

    @Override
    public ApplicationResponse getApplicationType() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        if(graduationApplicationRepository.findByReceiptCode(receiptCode).isPresent()){
            GraduationApplication graduationApplication = graduationApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);
            if(graduationApplication.getGraduateAt() != null)
                return applicantExportService.getApplicationType(receiptCode)
                    .setGraduatedAt(DateTimeFormatter.ofPattern("yyyyMM")
                    .format(graduationApplication.getGraduateAt()));
            return applicantExportService.getApplicationType(receiptCode);
        }
        return applicantExportService.getApplicationType(receiptCode);

    }

    @Override
    public InformationResponse getInformation() throws IOException {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = graduationApplicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(ApplicationNotFoundException::new);
        InformationResponse result = applicantExportService.getInformation(receiptCode);
        result.setPhotoFileName(getImageUrl(result.getPhotoFileName()));
        result.setSchoolCode(graduationApplication.getSchoolCode());
        result.setSchoolTel(graduationApplication.getSchoolTel());
        result.setIsGraduated(graduationApplication.getIsGraduated() != null
                && graduationApplication.getIsGraduated());
        result.setStudentNumber(graduationApplication.getStudentNumber());

        return result;
    }

    @Override
    public String uploadPhoto(MultipartFile multipartFile) throws IOException {
        long receiptCode = authenticationManager.getUserReceiptCode();
        InformationResponse result = applicantExportService.getInformation(receiptCode);
        if(result.getPhotoFileName() != null)
            imageService.delete(result.getPhotoFileName());
        String fileName = imageService.upload(multipartFile, receiptCode);
        applicantExportService.setPhotoFileName(receiptCode, fileName);
        return fileName;
    }

    @Override
    public void updateSubjectScore(SubjectScoreRequest score) {
        scoreService.updateSubjectScore(score);
    }

    @Override
    public void updateEtcScore(EtcScoreRequest score) {
        scoreService.updateEtcScore(score);
    }

    @Override
    public void updateGedScore(GedScoreRequest score) {
        scoreService.updateGedScore(score);
    }

    @Override
    public SubjectScoreResponse getSubjectScore() {
        return scoreService.getSubjectScore();
    }

    @Override
    public EtcScoreResponse getEtcScore() {
        return scoreService.getEtcScore();
    }

    @Override
    public GedScoreResponse getGedScore() {
        return scoreService.getGedScore();
    }


    private String getImageUrl(String photoFileName) throws MalformedURLException {
        return (photoFileName != null) ? imageService.generateObjectUrl(photoFileName) : null;
    }

    private GraduationApplication getGraduationApplication(long receiptCode) {
        GraduationApplication graduationApplication;
        if (graduationApplicationRepository.findByReceiptCode(receiptCode).isPresent()) {
            return graduationApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);
        } else {
            graduationApplication = new GraduationApplication();
            graduationApplication.setReceiptCode(receiptCode);
            return graduationApplication;
        }
    }

}
