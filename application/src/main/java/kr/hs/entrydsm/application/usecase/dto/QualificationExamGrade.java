package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class QualificationExamGrade {

    private final BigDecimal averageScore;
}
