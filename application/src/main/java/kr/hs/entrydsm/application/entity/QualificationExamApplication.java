package kr.hs.entrydsm.application.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_qualification_exam_application")
public class QualificationExamApplication extends Application {

    private BigDecimal averageScore;

    @Override
    public boolean isGraduation() {
        return false;
    }
}
