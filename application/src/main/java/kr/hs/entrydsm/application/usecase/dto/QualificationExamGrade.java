package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class QualificationExamGrade {

    private final BigDecimal averageScore;

}
