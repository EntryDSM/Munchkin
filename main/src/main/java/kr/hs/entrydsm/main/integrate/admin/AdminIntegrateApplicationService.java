package kr.hs.entrydsm.main.integrate.admin;

import java.net.MalformedURLException;

import kr.hs.entrydsm.admin.integrate.applicaton.ApplicationRepository;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ApplicantInfo;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ExcelUserInfo;
import kr.hs.entrydsm.application.integrate.admin.ApplicationExportAdminRepository;
import kr.hs.entrydsm.application.usecase.dto.MiddleSchoolInfo;
import kr.hs.entrydsm.application.usecase.dto.ReportCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminIntegrateApplicationService implements ApplicationRepository {

    private final ApplicationExportAdminRepository applicationExportRepository;

    @Override
    public ApplicantInfo getApplicantInfo(long receiptCode) {
        ReportCard reportCard = applicationExportRepository.getReportCard(receiptCode);

        return ApplicantInfo.builder()
                .averageScore(reportCard.getAverageScore())
                .isGraduated(reportCard.getIsGraduated())
                .volunteerTime(reportCard.getVolunteerTime())
                .schoolTel(reportCard.getSchoolTel())
                .schoolName(reportCard.getSchoolName())
                .latenessCount(reportCard.getLatenessCount())
                .earlyLeaveCount(reportCard.getEarlyLeaveCount())
                .lectureAbsenceCount(reportCard.getLectureAbsenceCount())
                .dayAbsenceCount(reportCard.getDayAbsenceCount())
                .conversionScore(reportCard.getTotalScore())
                .build();
    }

    @Override
    public ExcelUserInfo getExcelUserInfo(long receiptCode) {
        MiddleSchoolInfo middleSchoolInfo = applicationExportRepository.getMiddleSchoolInfo(receiptCode);

        return ExcelUserInfo.builder()
                .yearOfGraduation(middleSchoolInfo.getYearOfGraduation())
                .middleSchool(middleSchoolInfo.getMiddleSchool())
                .middleSchoolStudentNumber(middleSchoolInfo.getMiddleSchoolStudentNumber())
                .build();
    }

    @Override
    public String getPhotoUrl(String photoFileName) throws MalformedURLException {
        return applicationExportRepository.getFileUrl(photoFileName);
    }

}
