package kr.hs.entrydsm.score.entity;

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

    private int attendanceScore;

    @Digits(integer = 2, fraction = 3)
    private BigDecimal firstGradeScore;

    @Digits(integer = 2, fraction = 3)
    private BigDecimal secondGradeScore;

    @Digits(integer = 2, fraction = 3)
    private BigDecimal thirdGradeScore;

    @Digits(integer = 3, fraction = 3)
    private BigDecimal totalGradeScore;

    @Digits(integer = 3, fraction = 3)
    private BigDecimal totalScore;

    public Score(long receiptCode, ApplicationCase applicationCase) {
        this.receiptCode = receiptCode;
        this.volunteerScore = applicationCase.calculateVolunteerScore();
        this.attendanceScore = applicationCase.calculateAttendanceScore();

        BigDecimal[] gradeScores = applicationCase.calculateGradeScores();
        this.firstGradeScore = gradeScores[0];
        this.secondGradeScore = gradeScores[1];
        this.thirdGradeScore = gradeScores[2];

        this.totalGradeScore = applicationCase.calculateTotalGradeScore();
        this.totalScore = volunteerScore.add(BigDecimal.valueOf(attendanceScore)).add(totalGradeScore);
    }
}
