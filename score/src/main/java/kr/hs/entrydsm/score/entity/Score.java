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
    private BigDecimal thirdBeforeBeforeScore;

    @Digits(integer = 2, fraction = 3)
    private BigDecimal thirdBeforeScore;

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
        this.thirdBeforeBeforeScore = gradeScores[0];
        this.thirdBeforeScore = gradeScores[1];
        this.thirdGradeScore = gradeScores[2];

        this.totalGradeScore = applicationCase.calculateTotalGradeScore();
        this.totalScore = volunteerScore.add(BigDecimal.valueOf(attendanceScore)).add(totalGradeScore);
    }
}
