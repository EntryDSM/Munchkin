package kr.hs.entrydsm.application.builder;

import kr.hs.entrydsm.application.entity.QualificationExamApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

public class QualificationExamApplicationBuilder {

    public static QualificationExamApplication build() {
        return build(1L);
    }

    public static QualificationExamApplication build(long receiptCode) {
        return QualificationExamApplication.builder()
                .receiptCode(receiptCode)
                .qualifiedAt(LocalDate.of(2020, 8, 13))
                .build();
    }

    public static final QualificationExamApplication APPLICATION_1 =
            new QualificationExamApplication(1L);

    public static final QualificationExamApplication APPLICATION_2 =
            new QualificationExamApplication();

}
