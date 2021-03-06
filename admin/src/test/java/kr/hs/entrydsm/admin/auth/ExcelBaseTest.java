package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.usecase.dto.excel.ExcelUser;
import kr.hs.entrydsm.admin.usecase.dto.excel.ExcelUserScore;

import java.math.BigDecimal;

public class ExcelBaseTest {

    protected static final ExcelUser EXCEL_USER = ExcelUser.builder()
            .examCode("123456")
            .receiptCode(123456L)
            .applicationType("COMMON")
            .applicationRemark(null)
            .area("대전")
            .name("홍길동")
            .birthDay("2000-00-00")
            .address("대전광역시 유성구 가정북로 76")
            .telephoneNumber("010-0000-0000")
            .sex("남자")
            .educationalStatus("졸업예정자")
            .studyPlan("학업계획서")
            .selfIntroduce("자기소개서")
            .parentName("홍상순")
            .parentTel("010-0000-0000")
            .build();

    protected static final ExcelUserScore EXCEL_USER_SCORE = ExcelUserScore.builder()
            .koreanGrade("AAAAAA")
            .socialGrade("AAAAAA")
            .historyGrade("AAAAAA")
            .mathGrade("AAAAAA")
            .scienceGrade("AAAAAA")
            .englishGrade("AAAAAA")
            .techAndHomeGrade("AAAAAA")
            .totalFirstGradeScores(BigDecimal.valueOf(123))
            .totalSecondGradeScores(BigDecimal.valueOf(123))
            .totalThirdGradeScores(BigDecimal.valueOf(123))
            .volunteerScore(BigDecimal.valueOf(10))
            .volunteerTime(50)
            .dayAbsenceCount(1)
            .lectureAbsenceCount(1)
            .latenessCount(1)
            .earlyLeaveCount(1)
            .conversionScore(BigDecimal.valueOf(123))
            .attendanceScore(1)
            .totalScoreFirstRound(BigDecimal.valueOf(123))
            .build();


}
