package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.GraduationApplicationRepository;
import kr.hs.entrydsm.application.entity.School;
import kr.hs.entrydsm.application.entity.SchoolRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantDocsService;
import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.EtcScore;
import kr.hs.entrydsm.application.usecase.dto.Information;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
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

@RequiredArgsConstructor
@Service
public class ApplicationManager implements ApplicationProcessing {

    private final ImageService imageService;
    private final ApplicantDocsService applicantDocsService;
    private final ApplicationApplicantRepository applicantExportService;
    private final SchoolRepository schoolRepository;
    private final GraduationApplicationRepository graduationApplicationRepository;
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
    public void writeApplicationType(Application applicationRequest) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        applicantExportService.writeApplicationType(receiptCode, applicationRequest);
    }

    @Override
    public void writeInformation(Information information) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        graduationApplicationRepository.findByReceiptCode(receiptCode)
                .map(graduationApplication -> {
                    graduationApplication.setSchoolTel(information.getSchoolTel());
                    graduationApplication.setSchool(schoolRepository.findByCode(information.getSchoolCode())
                            .orElseThrow(SchoolNotFoundException::new));
                    graduationApplicationRepository.save(graduationApplication);
                    return graduationApplication;
                }).orElseThrow(ApplicationNotFoundException::new);
        applicantExportService.writeInformation(receiptCode, information);
    }

    @Override
    public Application getApplicationType() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        return applicantExportService.getApplicationType(receiptCode);
    }

    @Override
    public Information getInformation() throws IOException {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = graduationApplicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(ApplicationNotFoundException::new);
        Information result = applicantExportService.getInformation(receiptCode);
        result.setPhotoFileName(getImageUrl(result.getPhotoFileName()));
        result.setSchoolCode(graduationApplication.getSchoolCode());
        result.setSchoolTel(graduationApplication.getSchoolTel());
        return result;
    }

    @Override
    public String uploadPhoto(MultipartFile multipartFile) throws IOException {
        long receiptCode = authenticationManager.getUserReceiptCode();
        String fileName = imageService.upload(multipartFile, receiptCode);
        applicantExportService.setPhotoFileName(receiptCode, fileName);
        return fileName;
    }

    @Override
    public void updateSubjectScore(SubjectScore score) {
        long receiptCode = authenticationManager.getUserReceiptCode();
        GraduationApplication graduationApplication = graduationApplicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(ApplicationNotFoundException::new);

        graduationApplication.setMathScore(score.getMathScore());
        graduationApplication.setEnglishScore(score.getEnglishScore());
        graduationApplication.setHistoryScore(score.getHistoryScore());
        graduationApplication.setKoreanScore(score.getKoreanScore());
        graduationApplication.setSocialScore(score.getSocialScore());
        graduationApplication.setScienceScore(score.getScienceScore());
        graduationApplication.setTechAndHomeScore(score.getTechAndHomeScore());

        graduationApplicationRepository.save(graduationApplication);
    }

    @Override
    public void updateEtcScore(EtcScore score) {
        GraduationApplication graduationApplication = graduationApplicationRepository.findByReceiptCode(1L)
                .orElseThrow(ApplicationNotFoundException::new);

        graduationApplication.setVolunteerTime(score.getVolunteerTime());
        graduationApplication.setDayAbsenceCount(score.getDayAbsenceCount());
        graduationApplication.setLectureAbsenceCount(score.getLectureAbsenceCount());
        graduationApplication.setLatenessCount(score.getLatenessCount());
        graduationApplication.setEarlyLeaveCount(score.getEarlyLeaveCount());

        graduationApplicationRepository.save(graduationApplication);
    }

    private String getImageUrl(String photoFileName) throws MalformedURLException {
        return (!photoFileName.isEmpty()) ? imageService.generateObjectUrl(photoFileName) : null;
    }
}
