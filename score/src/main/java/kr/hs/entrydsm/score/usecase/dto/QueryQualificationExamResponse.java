package kr.hs.entrydsm.score.usecase.dto;

import kr.hs.entrydsm.score.integrate.application.QualificationExamCase;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class QueryQualificationExamResponse {
    private final BigDecimal averageScore;

    public QueryQualificationExamResponse(QualificationExamCase qualificationExamCase) {
        this.averageScore = qualificationExamCase.getAverageScore();
    }
}
