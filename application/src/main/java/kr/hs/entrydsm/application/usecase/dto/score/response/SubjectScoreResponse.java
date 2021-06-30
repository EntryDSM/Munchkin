package kr.hs.entrydsm.application.usecase.dto.score.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectScoreResponse {

    private String koreanScore;

    private String socialScore;

    private String historyScore;

    private String mathScore;

    private String scienceScore;

    private String englishScore;

    private String techAndHomeScore;

}

