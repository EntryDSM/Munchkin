package kr.hs.entrydsm.application.usecase.dto.score.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class GedScoreRequest {

    @NotNull
    @DecimalMin(value = "60.0")
    @DecimalMax(value = "100.0")
    @Digits(integer = 3, fraction = 2)
    private final BigDecimal gedAverageScore;

}
