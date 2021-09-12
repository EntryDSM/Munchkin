package kr.hs.entrydsm.score.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQualificationExamRequest {
    @DecimalMin(value = "60.0")
    @DecimalMax(value = "100.0")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal gedAverageScore;
}
