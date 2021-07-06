package kr.hs.entrydsm.admin.usecase.dto.score;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonScoreResponse {

    @JsonProperty(value = "141-150")
    private int score141_150;

    @JsonProperty(value = "131-140")
    private int score131_140;

    @JsonProperty(value = "121-130")
    private int score121_130;

    @JsonProperty(value = "111-120")
    private int score111_120;

    @JsonProperty(value = "101-110")
    private int score101_110;

    @JsonProperty(value = "91-100")
    private int score91_100;

    @JsonProperty(value = "81-90")
    private int score81_90;

    @JsonProperty(value = "-80")
    private int score_80;

    private Integer commonCount;

    private Double commonCompetitionRate;

    public void addScore(double score) {
        if (score <= 80) score_80++;
        else if (score <= 90) score81_90++;
        else if (score <= 100) score91_100++;
        else if (score <= 110) score101_110++;
        else if (score <= 120) score111_120++;
        else if (score <= 130) score121_130++;
        else if (score <= 140) score131_140++;
        else if (score <= 150) score141_150++;
    }

    public void updateCountAndRate(Integer count, Double competitionRate) {
        this.commonCount = count;
        this.commonCompetitionRate = competitionRate;
    }

}
