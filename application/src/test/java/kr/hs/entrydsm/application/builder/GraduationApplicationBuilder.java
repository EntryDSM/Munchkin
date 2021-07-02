package kr.hs.entrydsm.application.builder;

import kr.hs.entrydsm.application.entity.GraduationApplication;

import java.time.LocalDate;

public class GraduationApplicationBuilder {

    public static GraduationApplication build() {
        return build(1);
    }

    public static GraduationApplication buildEmptyStudentNumber() {
        return GraduationApplication.builder()
                .isGraduated(false)
                .studentNumber(null)
                .school(SchoolBuilder.build())
                .schoolTel("0420000000")
                .graduatedAt(LocalDate.of(2019, 2, 1))
                .build();
    }

    public static GraduationApplication build(long receiptCode) {
        return GraduationApplication.builder()
                .receiptCode(receiptCode)
                .isGraduated(false)
                .studentNumber("30401")
                .school(SchoolBuilder.build())
                .schoolTel("0420000000")
                .graduatedAt(LocalDate.of(2019, 2, 1))
                .build();
    }

    public static final GraduationApplication APPLICATION_1 = new GraduationApplication(1L);

    public static final GraduationApplication APPLICATION_2 = new GraduationApplication();
}
