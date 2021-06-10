package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.usecase.dto.ApplicationStatus;
import kr.hs.entrydsm.admin.usecase.dto.ExcelUserScore;
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
                .commonScore(statusResponse.getCommonScore())
                .meisterScore(statusResponse.getMeisterScore())
                .specialScore(statusResponse.getSpecialScore())
                .build();
    }

    @Override
    public ExcelUserScore findUserScore(Long receiptCode) {
        ApplicantScore score = scoreExportRepository.getApplicantScore(receiptCode);
        return ExcelUserScore.builder()
                .middleSchool(score.getMiddleSchool()) //출신학교
                .middleSchoolStudentNumber(score.getMiddleSchoolStudentNumber()) //중학교 학번 (반으로 분류)
                .koreanGrade(score.getKoreanGrade()) //국어 점수
                .socialGrade(score.getSocialGrade()) //사회 점수
                .historyGrade(score.getHistoryGrade()) //역사 점수
                .mathGrade(score.getMathGrade()) //수학 점수
                .scienceGrade(score.getScienceGrade()) //과학 점수
                .englishGrade(score.getEnglishGrade()) //영어 점수
                .techAndHomeGrade(score.getTechAndHomeGrade()) //기술가정 점수
                .totalFirstGradeScores(score.getTotalFirstGradeScores()) //1학년 성적 총합
                .totalSecondGradeScores(score.getTotalSecondGradeScores()) //2학년 성적 총합
                .totalThirdGradeScores(score.getTotalThirdGradeScores()) //3학년 성적 총합
                .volunteerTime(score.getVolunteerTime()) //봉사 시간
                .volunteerScore(score.getVolunteerScore()) //봉사 점수
                .dayAbsenceCount(score.getDayAbsenceCount()) //무단 결석
                .lectureAbsenceCount(score.getLectureAbsenceCount()) //무단 결과
                .latenessCount(score.getLatenessCount()) //지각
                .earlyLeaveCount(score.getEarlyLeaveCount()) //조퇴
                .conversionScore(score.getConversionScore()) //교과 성적 환산 점수
                .attendanceScore(score.getAttendanceScore()) //출석 점수
                .totalScoreFirstRound(score.getTotalScoreFirstRound()) //1차 전형 총점
                .yearOfGraduation(score.getYearOfGraduation())
                .build();
    }

}
