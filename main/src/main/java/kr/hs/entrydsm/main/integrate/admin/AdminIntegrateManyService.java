//package kr.hs.entrydsm.main.integrate.admin;
//
//import kr.hs.entrydsm.admin.entity.ExcelApplicant;
//import kr.hs.entrydsm.admin.integrate.user.ExcelApplicantRepository;
//import kr.hs.entrydsm.application.entity.GraduationApplication;
//import kr.hs.entrydsm.application.integrate.admin.ApplicationExportRepository;
//import kr.hs.entrydsm.application.usecase.dto.Applicant;
//import kr.hs.entrydsm.user.entity.User;
//import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Component
//public class AdminIntegrateManyService implements ExcelApplicantRepository {
//
//    private final UserExportRepository userExportRepository;
//    private final ApplicationExportRepository applicationExportRepository;
//
//
//    @Override
//    public List<ExcelApplicant> findAllForExcel() {
//        List<User> users = userExportRepository.findAllForExcel();
//        List<Applicant> applicants = applicationExportRepository.getApplicants();
//
//
//        List<ExcelApplicant> excelApplicants = new ArrayList<>();
//        for(int i=0,size=users.size(); i<size; i++) {
//            User user = users.get(i);
//            Applicant applicant = applicants.get(i);
//            excelApplicants.add(
//                    ExcelApplicant.builder()
//                            .examCode(null) //수험번호
//                            .receiptCode(null) //접수 번호
//                            .applicationType(null) //전형 유형
//                            .applicationRemrk(null) //추가 유형
//                            .area(null) //지역
//                            .name(null) //이름
//                            .birthDay(null) //생년월일
//                            .address(null) //주소
//                            .middleSchool(null) //출신학교
//                            .middleSchoolStudentNumber(null) //중학교 학번 (반으로 분류)
//                            .telephoneNumber(null) //학생 전화번호
//                            .studyPlan(null) //자기소개서
//                            .selfIntroduce(null) //학업 계획서
//                            .parentName(null) //보호자 이름
//                            .parentTel(null) //보호자 전화번호
//                            .koreanGrade(null) //국어 점수
//                            .socialGrade(null) //사회 점수
//                            .historyGrade(null) //역사 점수
//                            .mathGrade(null) //수학 점수
//                            .scienceGrade(null) //과학 점수
//                            .englishGrade(null) //영어 점수
//                            .techAndHomeGrade(null) //기술가정 점수
//                            .totalFirstGradeScores(null) //1학년 성적 총합
//                            .totalSecondGradeScores(null) //2학년 성적 총합
//                            .totalThirdGradeScores(null) //3학년 성적 총합
//                            .volunteerTime(null) //봉사 시간
//                            .volunteerScore(null) //봉사 점수
//                            .dayAbsenceCount(null) //무단 결석
//                            .lectureAbsenceCount(null) //무단 결과
//                            .latenessCount(null) //지각
//                            .earlyLeaveCount(null) //조퇴
//                            .conversionScore(null) //교과 성적 환산 점수
//                            .attendanceScore(null) //출석 점수
//                            .totalScoreFirstRound(null) //1차 전형 총점
//                            .build()
//            );
//        }
//        return excelApplicants;
//    }
//
//}
