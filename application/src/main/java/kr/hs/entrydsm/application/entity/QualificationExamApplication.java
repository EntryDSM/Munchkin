package kr.hs.entrydsm.application.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_qualification_exam_application")
public class QualificationExamApplication extends Application {

    private BigDecimal averageScore;

    private LocalDate qualifiedAt;

    @Builder
    public QualificationExamApplication(long receiptCode, BigDecimal averageScore, LocalDate qualifiedAt) {
        super(receiptCode);
        this.averageScore = averageScore;
        this.qualifiedAt = qualifiedAt;
    }

    @Override
    public boolean isGraduation() {
        return false;
    }
}
