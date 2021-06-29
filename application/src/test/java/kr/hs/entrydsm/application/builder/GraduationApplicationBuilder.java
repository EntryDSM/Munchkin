package kr.hs.entrydsm.application.builder;

import kr.hs.entrydsm.application.entity.GraduationApplication;

import java.time.LocalDate;

public class GraduationApplicationBuilder {

    public static GraduationApplication build() {
        return build(1);
    }

    public static GraduationApplication buildEmptyStudentNumber() {
        return GraduationApplication.builder()
                .receiptCode(1)
                .isGraduated(false)
                .studentNumber(null)
                .school(SchoolBuilder.build())
                .schoolTel("0420000000")
                .volunteerTime(15)
                .dayAbsenceCount(0)
                .lectureAbsenceCount(0)
                .latenessCount(0)
                .earlyLeaveCount(0)
                .koreanScore("AXAAAA")
                .englishScore("BXBBAA")
                .historyScore("AXAAAA")
                .mathScore("BXBBAA")
                .scienceScore("AXAAAA")
                .socialScore("AXAAAA")
                .techAndHomeScore("AXAAAA")
                .graduateAt(LocalDate.of(2019, 2, 1))
                .build();
    }

    public static GraduationApplication build(long receiptCode) {
        return GraduationApplication.builder()
                .receiptCode(receiptCode)
                .isGraduated(false)
                .studentNumber("30401")
                .school(SchoolBuilder.build())
                .schoolTel("0420000000")
                .volunteerTime(15)
                .dayAbsenceCount(0)
                .lectureAbsenceCount(0)
                .latenessCount(0)
                .earlyLeaveCount(0)
                .koreanScore("AXAAAA")
                .englishScore("BXBBAA")
                .historyScore("AXAAAA")
                .mathScore("BXBBAA")
                .scienceScore("AXAAAA")
                .socialScore("AXAAAA")
                .techAndHomeScore("AXAAAA")
                .graduateAt(LocalDate.of(2019, 2, 1))
                .build();
    }
}