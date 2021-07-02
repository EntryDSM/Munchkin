package kr.hs.entrydsm.application.entity;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tbl_qualification_exam_application")
public class QualificationExamApplication extends Application {


    private LocalDate qualifiedAt;

    public QualificationExamApplication(long receiptCode) {
        super(receiptCode);
    }

    @Builder
    public QualificationExamApplication(long receiptCode, LocalDate qualifiedAt) {
        super(receiptCode);
        this.qualifiedAt = qualifiedAt;
    }

    @Override
    public boolean isGraduation() {
        return false;
    }
}
