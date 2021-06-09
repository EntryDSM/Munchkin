package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.GraduationApplicationRepository;
import kr.hs.entrydsm.application.entity.School;
import kr.hs.entrydsm.application.entity.SchoolRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantDocsService;
import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.application.usecase.exception.SchoolNotFoundException;
import kr.hs.entrydsm.application.usecase.image.ImageService;
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

    @Override
    public void writeSelfIntroduce(Long receiptCode, String content) {
        applicantDocsService.writeSelfIntroduce(receiptCode, content);
    }

    @Override
    public void writeStudyPlan(Long receiptCode, String content) {
        applicantDocsService.writeStudyPlan(receiptCode, content);
    }

    @Override
    public String getSelfIntroduce(Long receiptCode) {
        return applicantDocsService.getSelfIntroduce(receiptCode);
    }

    @Override
    public String getStudyPlan(Long receiptCode) {
        return applicantDocsService.getStudyPlan(receiptCode);
    }

    @Override
    public Page<School> getSchoolsByInformation(String information, Pageable pageable) {
        return schoolRepository.findByInformationContains(information, pageable);
    }

    @Override
    public void writeApplicationType(Long receiptCode, Application applicationRequest) {
        applicantExportService.writeApplicationType(receiptCode, applicationRequest);
    }

    @Override
    public void writeInformation(Long receiptCode, Information information) {
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
    public Application getApplicationType(Long receiptCode) {
        return applicantExportService.getApplicationType(receiptCode);
    }

    @Override
    public Information getInformation(Long receiptCode) throws IOException {
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
        String fileName = imageService.upload(multipartFile, 1L);
        applicantExportService.setPhotoFileName(1L, fileName);
        return fileName;
    }

    @Override
    public void updateSubjectScore(SubjectScore score) {
        GraduationApplication graduationApplication = graduationApplicationRepository.findByReceiptCode(1L)
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

    private String getImageUrl(String photoFileName) throws MalformedURLException {
        return (!photoFileName.isEmpty()) ? imageService.generateObjectUrl(photoFileName) : null;
    }
}
