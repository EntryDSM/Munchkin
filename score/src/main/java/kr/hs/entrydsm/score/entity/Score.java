package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.application.ApplicationCase;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity(name = "tbl_calculated_score")
public class Score {

    @Id
    private long receiptCode;

    @Digits(integer = 2, fraction = 3)
    private BigDecimal volunteerScore;

    private Integer attendanceScore;

    @Digits(integer = 3, fraction = 3)
    private BigDecimal gradeScore;

    @Digits(integer = 3, fraction = 3)
    private BigDecimal totalScore;

    public Score(long receiptCode, ApplicationCase applicationCase) {
        this.receiptCode = receiptCode;
        this.volunteerScore = applicationCase.calculateVolunteerScore();
        this.attendanceScore = applicationCase.calculateAttendanceScore();
        this.gradeScore = applicationCase.calculateGradeScore();
        this.totalScore = volunteerScore.add(BigDecimal.valueOf(attendanceScore)).add(gradeScore);
    }
}
