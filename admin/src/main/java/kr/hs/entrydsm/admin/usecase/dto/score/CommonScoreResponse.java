package kr.hs.entrydsm.admin.usecase.dto.score;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonScoreResponse {

    @JsonProperty(value = "158-170")
    private int score158_170;

    @JsonProperty(value = "145-157")
    private int score145_157;

    @JsonProperty(value = "132-144")
    private int score132_144;

    @JsonProperty(value = "119-131")
    private int score119_131;

    @JsonProperty(value = "106-118")
    private int score106_118;

    @JsonProperty(value = "93-105")
    private int score93_105;

    @JsonProperty(value = "80-92")
    private int score80_92;

    @JsonProperty(value = "-79")
    private int score_79;

    private Integer commonCount;

    private Double commonCompetitionRate;

    public void addScore(double score) {
        if(score <= 79) score_79++;
        else if (score <= 92) score80_92++;
        else if (score <= 105) score93_105++;
        else if (score <= 118) score106_118++;
        else if (score <= 131) score119_131++;
        else if (score <= 144) score132_144++;
        else if (score <= 157) score145_157++;
        else if (score <= 170) score158_170++;
    }

    public void updateCountAndRate(Integer count, Double competitionRate) {
        this.commonCount = count;
        this.commonCompetitionRate = competitionRate;
    }

}
