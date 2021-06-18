package kr.hs.entrydsm.application.usecase.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubjectScore {

    @NotNull
    private String koreanScore;

    @NotNull
    private String socialScore;

    @NotNull
    private String historyScore;

    @NotNull
    private String mathScore;

    @NotNull
    private String scienceScore;

    @NotNull
    private String englishScore;

    @NotNull
    private String techAndHomeScore;

    public static SubjectScore from(GraduationApplication application) {
        return SubjectScore.builder()
                .koreanScore(application.getKoreanScore())
                .mathScore(application.getMathScore())
                .historyScore(application.getHistoryScore())
                .englishScore(application.getEnglishScore())
                .socialScore(application.getSocialScore())
                .scienceScore(application.getScienceScore())
                .techAndHomeScore(application.getTechAndHomeScore())
                .build();
    }

}
