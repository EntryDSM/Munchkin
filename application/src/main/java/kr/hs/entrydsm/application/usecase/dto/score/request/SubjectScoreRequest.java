package kr.hs.entrydsm.application.usecase.dto.score.request;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
@Builder
@AllArgsConstructor
public class SubjectScoreRequest {

    @Pattern(regexp = "[A-E,X]{6}")
    private final String koreanScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String socialScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String historyScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String mathScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String scienceScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String englishScore;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String techAndHomeScore;

    public static SubjectScoreRequest from(GraduationApplication application) {
        return SubjectScoreRequest.builder()
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
