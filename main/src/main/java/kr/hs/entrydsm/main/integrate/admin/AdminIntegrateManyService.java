package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.entity.ExcelApplicant;
import kr.hs.entrydsm.admin.integrate.user.ExcelApplicantRepository;
import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.integrate.admin.ApplicationExportRepository;
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
        List<GraduationApplication> applicants = applicationExportRepository.getApplicants();


        List<ExcelApplicant> excelApplicants = new ArrayList<>();
        for(int i=0,size=users.size(); i<size; i++) {
            User user = users.get(i);
            GraduationApplication graduationApplication = applicants.get(i);
            excelApplicants.add(
                    ExcelApplicant.builder()
                            .examCode() //수험번호
                            .receiptCode(String.valueOf(graduationApplication.getReceiptCode())) //접수 번호
                            .applicationType(user.getApplicationType().toString()) //전형 유형
                            .applicationRemrk(user.getApplicationRemark().toString()) //추가 유형
                            .area(user.isDaejeon()) //지역
                            .name(user.getName()) //이름
                            .birthDay(user.getBirthday().toString()) //생년월일
                            .address(user.getAddress()) //주소
                            .middleSchool(graduationApplication.getSchoolName()) //출신학교
                            .middleSchoolStudentNumber(graduationApplication.getStudentNumber()) //중학교 학번 (반으로 분류)
                            .telephoneNumber(user.getTelephoneNumber()) //학생 전화번호
                            .studyPlan(user.getSelfIntroduce()) //자기소개서
                            .selfIntroduce(user.getStudyPlan()) //학업 계획서
                            .parentName(user.getParentName()) //보호자 이름
                            .parentTel(user.getParentTel()) //보호자 전화번호
                            .koreanGrade(graduationApplication.getKoreanScore()) //국어 점수
                            .socialGrade(graduationApplication.getSocialScore()) //사회 점수
                            .historyGrade(graduationApplication.getHistoryScore()) //역사 점수
                            .mathGrade(graduationApplication.getMathScore()) //수학 점수
                            .scienceGrade(graduationApplication.getScienceScore()) //과학 점수
                            .englishGrade(graduationApplication.getEnglishScore()) //영어 점수
                            .techAndHomeGrade(graduationApplication.getTechAndHomeScore()) //기술가정 점수
                            .totalFirstGradeScores(null) //1학년 성적 총합
                            .totalSecondGradeScores(null) //2학년 성적 총합
                            .totalThirdGradeScores(null) //3학년 성적 총합
                            .volunteerTime(String.valueOf(graduationApplication.getVolunteerTime())) //봉사 시간
                            .volunteerScore(null) //봉사 점수
                            .dayAbsenceCount(String.valueOf(graduationApplication.getDayAbsenceCount())) //무단 결석
                            .lectureAbsenceCount(String.valueOf(graduationApplication.getLectureAbsenceCount())) //무단 결과
                            .latenessCount(String.valueOf(graduationApplication.getLatenessCount())) //지각
                            .earlyLeaveCount(String.valueOf(graduationApplication.getEarlyLeaveCount())) //조퇴
                            .conversionScore(null) //교과 성적 환산 점수
                            .attendanceScore(null) //출석 점수
                            .totalScoreFirstRound(null) //1차 전형 총점
                            .build()
            );
        }
        return excelApplicants;
    }

}
