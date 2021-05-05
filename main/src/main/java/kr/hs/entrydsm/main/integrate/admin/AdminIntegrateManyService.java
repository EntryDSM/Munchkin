package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.usecase.dto.ExcelApplicant;
import kr.hs.entrydsm.admin.integrate.user.ExcelApplicantRepository;
import kr.hs.entrydsm.application.integrate.admin.ApplicationExportRepository;
import kr.hs.entrydsm.user.entity.User;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AdminIntegrateManyService implements ExcelApplicantRepository {

    private final UserExportRepository userExportRepository;
    private final ApplicationExportRepository applicationExportRepository;


    @Override
    public List<ExcelApplicant> findAllForExcel() {
        List<User> users = userExportRepository.findAllForExcel();
        //List<Applicant> applicants = applicationExportRepository

        List<ExcelApplicant> excelApplicants = new ArrayList<>();
        for(User user : users) {
            excelApplicants.add(
                    ExcelApplicant.builder()
                            .examCode(user.getStatus().getExamCode()) //수험번호
                            .receiptCode(String.valueOf(user.getReceiptCode())) //접수 번호
                            .applicationType(String.valueOf(user.getApplicationType())) //전형 유형
                            .applicationRemrk(String.valueOf(user.getApplicationRemark())) //추가 유형
                            .area(String.valueOf(user.isDaejeon())) //지역
                            .name(user.getName()) //이름
                            .birthDay(user.getBirthday().toString()) //생년월일
                            .address(user.getAddress()) //주소
                            .middleSchool(null) //출신학교
                            .middleSchoolStudentNumber(null) //중학교 학번 (반으로 분류)
                            .telephoneNumber(user.getTelephoneNumber()) //학생 전화번호
                            .studyPlan(user.getStudyPlan()) //자기소개서
                            .selfIntroduce(user.getSelfIntroduce()) //학업 계획서
                            .parentName(user.getParentName()) //보호자 이름
                            .parentTel(user.getParentTel()) //보호자 전화번호
                            .koreanGrade(null) //국어 점수
                            .socialGrade(null) //사회 점수
                            .historyGrade(null) //역사 점수
                            .mathGrade(null) //수학 점수
                            .scienceGrade(null) //과학 점수
                            .englishGrade(null) //영어 점수
                            .techAndHomeGrade(null) //기술가정 점수
                            .totalFirstGradeScores(null) //1학년 성적 총합
                            .totalSecondGradeScores(null) //2학년 성적 총합
                            .totalThirdGradeScores(null) //3학년 성적 총합
                            .volunteerTime(null) //봉사 시간
                            .volunteerScore(null) //봉사 점수
                            .dayAbsenceCount(null) //무단 결석
                            .lectureAbsenceCount(null) //무단 결과
                            .latenessCount(null) //지각
                            .earlyLeaveCount(null) //조퇴
                            .conversionScore(null) //교과 성적 환산 점수
                            .attendanceScore(null) //출석 점수
                            .totalScoreFirstRound(null) //1차 전형 총점
                            .build()
            );
        }
        return excelApplicants;
    }

}
