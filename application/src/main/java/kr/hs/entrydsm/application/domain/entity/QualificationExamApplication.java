package kr.hs.entrydsm.application.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_qualification_exam_application")
public class QualificationExamApplication extends Application {

    @Digits(integer = 3, fraction = 2)
    private BigDecimal averageScore;

    @Override
    public boolean isGraduation() {
        return false;
    }
}
