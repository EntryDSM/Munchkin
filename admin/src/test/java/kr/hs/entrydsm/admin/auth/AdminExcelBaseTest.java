package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.usecase.dto.ExcelUser;
import kr.hs.entrydsm.admin.usecase.dto.ExcelUserScore;

import java.time.LocalDate;

public class AdminExcelBaseTest {

    protected static final ExcelUser EXCEL_USER = ExcelUser.builder()
            .examCode("12036") //수험번호
            .receiptCode(123456L) //접수 번호
            .applicationType("MEISTER") //전형 유형 COMMON, MEISTER, SOCIAL
            .applicationRemrk(null) //추가유형 ONE_PARENT, FROM_NORTH, MULTICULTURAL, BASIC_LIVING, LOWEST_INCOME, TEEN_HOUSEHOLDER
            .area("전국") //지역
            .name("리슴뮴") //이름
            .birthDay("2000-00-00") //생년월일
            .address("서울특별시") //주소
            .telephoneNumber("010-0000-0000") //학생 전화번호
            .sex("여자") //성별
            .educationalStatus("PROSPECTIVE_GRADUATE") //학력구분 PROSPECTIVE_GRADUATE, GRADUATE, QUALIFICATION_EXAM
            .studyPlan("학업 계획서") //학업 계획서
            .selfIntroduce("자기 소개서") //자기소개서
            .parentName("홍길동") //보호자 이름
            .parentTel("010-8888-8888") //보호자 전화번호
            .build();

    protected static final ExcelUserScore EXCEL_USER_SCORE = ExcelUserScore.builder()
            .yearOfGraduation("2021") //졸업 년도
            .middleSchoolStudentNumber("30216") //중학교 학번
            .middleSchool("고양이중학교") //출신 중학교
            .koreanGrade("XAAAAA") //국어 점수
            .socialGrade("XAAAAA") //사회 점수
            .historyGrade("XAAAAA") //역사 점수
            .mathGrade("XAAAAA") //수학 점수
            .scienceGrade("XAAAAA") //과학 점수
            .englishGrade("XAAAAA") //영어 점수
            .techAndHomeGrade("XAAAAA") //기술과 가정 점수
            .totalFirstGradeScores("50") //1학년 성적 총합
            .totalSecondGradeScores("50") //2학년 성적 총합
            .totalThirdGradeScores("50") //3학년 성적 총합
            .volunteerTime("101") //봉사 시간
            .volunteerScore("15") //봉사 점수
            .dayAbsenceCount("0") //무단 결석
            .lectureAbsenceCount("0") //무단 결과
            .latenessCount("0") //지각
            .earlyLeaveCount("0") //조퇴
            .conversionScore("147.8") //교과 성적 환산 점수
            .attendanceScore("10") //출석 점수
            .totalScoreFirstRound("178.5") //1차 전형 총점
            .build();

}
