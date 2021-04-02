package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.entity.ExcelApplicant;
import kr.hs.entrydsm.admin.integrate.user.ExcelApplicantRepository;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.integrate.admin.ApplicationExportRepository;
import kr.hs.entrydsm.application.usecase.dto.Applicant;
import kr.hs.entrydsm.user.entity.User;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AdminIntegrateManyService implements ExcelApplicantRepository {

    private final UserExportRepository userExportRepository;
    private final ApplicationExportRepository applicationExportRepository;


    @Override
    public List<ExcelApplicant> findAllForExcel() {
        List<User> users = userExportRepository.findAllForExcel();
        List<Applicant> applicants = applicationExportRepository.getApplicants();


        List<ExcelApplicant> excelApplicants = new ArrayList<>();
        for(int i=0,size=users.size(); i<size; i++) {
            User user = users.get(i);
            Applicant applicant = applicants.get(i);
            excelApplicants.add(
                    ExcelApplicant.builder()
                            .examCode(applicant.getExamCode()) //수험번호
                            .receiptCode(String.valueOf(applicant.getReceiptCode())) //접수 번호
                            .applicationType(user.getApplicationType().toString()) //전형 유형
                            .applicationRemrk(user.getApplicationRemark().toString()) //추가 유형
                            .area(user.isDaejeon()) //지역
                            .name(user.getName()) //이름
                            .birthDay(user.getBirthday().toString()) //생년월일
                            .address(user.getAddress()) //주소
                            .middleSchool(applicant.getSchoolName()) //출신학교
                            .middleSchoolStudentNumber(applicant.getStudentNumber()) //중학교 학번 (반으로 분류)
                            .telephoneNumber(user.getTelephoneNumber()) //학생 전화번호
                            .studyPlan(user.getSelfIntroduce()) //자기소개서
                            .selfIntroduce(user.getStudyPlan()) //학업 계획서
                            .parentName(user.getParentName()) //보호자 이름
                            .parentTel(user.getParentTel()) //보호자 전화번호
                            .koreanGrade(applicant.getKoreanScore()) //국어 점수
                            .socialGrade(applicant.getSocialScore()) //사회 점수
                            .historyGrade(applicant.getHistoryScore()) //역사 점수
                            .mathGrade(applicant.getMathScore()) //수학 점수
                            .scienceGrade(applicant.getScienceScore()) //과학 점수
                            .englishGrade(applicant.getEnglishScore()) //영어 점수
                            .techAndHomeGrade(applicant.getTechAndHomeScore()) //기술가정 점수
                            .totalFirstGradeScores(applicant.getTotalFirstGradeScores()) //1학년 성적 총합
                            .totalSecondGradeScores(applicant.getTotalSecondGradeScores()) //2학년 성적 총합
                            .totalThirdGradeScores(applicant.getTotalThirdGradeScores()) //3학년 성적 총합
                            .volunteerTime(String.valueOf(applicant.getVolunteerTime())) //봉사 시간
                            .volunteerScore(applicant.getVolunteerScore()) //봉사 점수
                            .dayAbsenceCount(String.valueOf(applicant.getDayAbsenceCount())) //무단 결석
                            .lectureAbsenceCount(String.valueOf(applicant.getLectureAbsenceCount())) //무단 결과
                            .latenessCount(String.valueOf(applicant.getLatenessCount())) //지각
                            .earlyLeaveCount(String.valueOf(applicant.getEarlyLeaveCount())) //조퇴
                            .conversionScore(applicant.getConversionScore()) //교과 성적 환산 점수
                            .attendanceScore(applicant.getAttendanceScore()) //출석 점수
                            .totalScoreFirstRound(applicant.getTotalScoreFirstRound()) //1차 전형 총점
                            .build()
            );
        }
        return excelApplicants;
    }

}
