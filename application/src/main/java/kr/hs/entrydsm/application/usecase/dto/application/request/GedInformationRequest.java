package kr.hs.entrydsm.application.usecase.dto.application.request;

import kr.hs.entrydsm.application.usecase.dto.application.Information;
import lombok.Getter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
public class GedInformationRequest extends Information {

    @NotNull
    @DecimalMin(value = "60.0")
    @DecimalMax(value = "100.0")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal gedAverageScore;

}
