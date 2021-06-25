package kr.hs.entrydsm.application.usecase.dto;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectScore {

    @Pattern(regexp = "[A-E,X]{6}")
    private String koreanScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private String socialScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private String historyScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private String mathScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private String scienceScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private String englishScore;

    @Pattern(regexp = "[A-E,X]{6}")
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
