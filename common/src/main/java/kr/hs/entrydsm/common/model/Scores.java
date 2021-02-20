package kr.hs.entrydsm.common.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Scores {

    private BigDecimal totalScore;
    private BigDecimal volunteerScore;
    private BigDecimal gradeScore;
    private int attendanceScore;
}
