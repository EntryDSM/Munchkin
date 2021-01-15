package kr.hs.entrydsm.score.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_calculated_score")
public class Score {

    @Id
    private long receiptCode;

    @Digits(integer = 2, fraction = 3)
    private BigDecimal volunteerScore;

    private Integer attendanceScore;

    @Digits(integer = 2, fraction = 3)
    private BigDecimal firstGradeScore;

    @Digits(integer = 2, fraction = 3)
    private BigDecimal secondGradeScore;

    @Digits(integer = 2, fraction = 3)
    private BigDecimal thirdGradeScore;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;
}
