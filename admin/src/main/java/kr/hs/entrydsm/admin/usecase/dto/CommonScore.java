package kr.hs.entrydsm.admin.usecase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
public class CommonScore {

    @JsonFormat(pattern = "141-150")
    private int score141_150;

    @JsonFormat(pattern = "131-140")
    private int score131_140;

    @JsonFormat(pattern = "121-130")
    private int score121_130;

    @JsonFormat(pattern = "111-120")
    private int score111_120;

    @JsonFormat(pattern = "101-110")
    private int score101_110;

    @JsonFormat(pattern = "91-100")
    private int score91_100;

    @JsonFormat(pattern = "81-90")
    private int score81_90;

    @JsonFormat(pattern = "-80")
    private int score_80;

}
