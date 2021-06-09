package kr.hs.entrydsm.admin.usecase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialScore {

    @JsonFormat(pattern = "81-90")
    private int score81_90;

    @JsonFormat(pattern = "71-80")
    private int score71_80;

    @JsonFormat(pattern = "61-70")
    private int score61_70;

    @JsonFormat(pattern = "51-60")
    private int score51_60;

    @JsonFormat(pattern = "41-50")
    private int score41_50;

    @JsonFormat(pattern = "31-40")
    private int score31_40;

    @JsonFormat(pattern = "21-30")
    private int score21_30;

    @JsonFormat(pattern = "-20")
    private int score_20;

    public void addScore(double score) {
        if (score <= 20) score_20++;
        else if(score <= 30) score21_30++;
        else if(score <= 40) score31_40++;
        else if(score <= 50) score41_50++;
        else if(score <= 60) score51_60++;
        else if(score <= 70) score61_70++;
        else if(score <= 80) score71_80++;
        else if (score <= 90) score81_90++;
    }

}
