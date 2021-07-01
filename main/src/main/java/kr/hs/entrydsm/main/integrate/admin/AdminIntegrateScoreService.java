package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ApplicationStatus;
import kr.hs.entrydsm.admin.usecase.dto.excel.ExcelUserScore;
import kr.hs.entrydsm.score.integrate.admin.ScoreExportAdminRepository;
import kr.hs.entrydsm.score.usecase.dto.ApplicantScore;
import kr.hs.entrydsm.score.usecase.dto.ApplicationStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminIntegrateScoreService implements ScoreRepository {

    private final ScoreExportAdminRepository scoreExportRepository;

    @Override
    public ApplicationStatus getScore() {
        ApplicationStatusResponse statusResponse = scoreExportRepository.getApplicationStatus();

        return ApplicationStatus.builder()
                .commonScores(statusResponse.getCommonScore())
                .meisterScores(statusResponse.getMeisterScore())
                .specialScores(statusResponse.getSpecialScore())
                .build();
    }

    @Override
    public ExcelUserScore findUserScore(Long receiptCode) {
        ApplicantScore score = scoreExportRepository.getApplicantScore(receiptCode);
        return ExcelUserScore.builder()
                .koreanGrade(score.getKoreanGrade())
                .socialGrade(score.getSocialGrade())
                .historyGrade(score.getHistoryGrade())
                .mathGrade(score.getMathGrade())
                .scienceGrade(score.getScienceGrade())
                .englishGrade(score.getEnglishGrade())
                .techAndHomeGrade(score.getTechAndHomeGrade())
                .totalFirstGradeScores(score.getTotalFirstGradeScores())
                .totalSecondGradeScores(score.getTotalSecondGradeScores())
                .totalThirdGradeScores(score.getTotalThirdGradeScores())
                .volunteerTime(score.getVolunteerTime())
                .volunteerScore(score.getVolunteerScore())
                .dayAbsenceCount(score.getDayAbsenceCount())
                .lectureAbsenceCount(score.getLectureAbsenceCount())
                .latenessCount(score.getLatenessCount())
                .earlyLeaveCount(score.getEarlyLeaveCount())
                .conversionScore(score.getConversionScore())
                .attendanceScore(score.getAttendanceScore())
                .totalScoreFirstRound(score.getTotalScoreFirstRound())
                .build();
    }

}
