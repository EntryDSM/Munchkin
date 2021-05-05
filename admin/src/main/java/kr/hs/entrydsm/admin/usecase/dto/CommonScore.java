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

    public void plus80() {
        this.score_80++;
    }

    public void plus81_90() {
        this.score81_90++;
    }

    public void plus91_100() {
        this.score91_100++;
    }

    public void plus101_110() {
        this.score91_100++;
    }

    public void plus111_120() {
        this.score111_120++;
    }

    public void plus121_130() {
        this.score121_130++;
    }

    public void plus131_140() {
        this.score131_140++;
    }

    public void plus141_150() {
        this.score141_150++;
    }

}
