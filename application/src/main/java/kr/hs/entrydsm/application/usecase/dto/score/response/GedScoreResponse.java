package kr.hs.entrydsm.application.usecase.dto.score.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GedScoreResponse {

    private BigDecimal gedAverageScore;

}
