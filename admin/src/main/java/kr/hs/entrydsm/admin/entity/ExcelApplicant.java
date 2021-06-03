package kr.hs.entrydsm.admin.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExcelApplicant {

    private String examCode; //수험번호

    private String receiptCode; //접수 번호

    private String applicationType; //전형 유형

    private String applicationRemrk; //추가유형

    private String area; //지역

    private String name; //이름

    private String birthDay; //생년월일

    private String address; //주소

    private String middleSchool; //출신학교
    
    private String yearOfGraduation; //졸업년도

    private String middleSchoolStudentNumber; //중학교 학번

    private String telephoneNumber; //학생 전화번호

    private String sex; //성별

    private String educationalStatus; //학력구분

    private String studyPlan; //자기소개서

    private String selfIntroduce; //학업 계획서

    private String parentName; //보호자 이름

    private String parentTel; //보호자 전화번호

    //점수
    private String koreanGrade; //국어 점수

    private String socialGrade; //사회 점수

    private String historyGrade; //역사 점수

    private String mathGrade; //수학 점수

    private String scienceGrade; //과학 점수

    private String englishGrade; //영어 점수

    private String techAndHomeGrade; //기술가정 점수

    private String totalFirstGradeScores; //1학년 성적 총합

    private String totalSecondGradeScores; //2학년 성적 총합

    private String totalThirdGradeScores; //3학년 성적 총합

    private String volunteerTime; //봉사 시간

    private String volunteerScore; //봉사 점수

    private String dayAbsenceCount; //무단 결석

    private String lectureAbsenceCount; //무단 결과

    private String latenessCount; //지각

    private String earlyLeaveCount; //조퇴

    private String conversionScore; //교과 성적 환산 점수

    private String attendanceScore; //출석 점수

    private String totalScoreFirstRound; //1차 전형 총점

}
