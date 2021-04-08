package kr.hs.entrydsm.admin.usecase.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
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

}
