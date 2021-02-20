package kr.hs.entrydsm.common.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter(AccessLevel.PROTECTED)
public class Scores {

    private final BigDecimal totalScore;
    private final BigDecimal volunteerScore;
    private final BigDecimal gradeScore;
    private final int attendanceScore;
}
