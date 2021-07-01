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
}
