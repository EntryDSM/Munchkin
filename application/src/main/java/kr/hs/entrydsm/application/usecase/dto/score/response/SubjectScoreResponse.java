package kr.hs.entrydsm.application.usecase.dto.score.response;

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
public class SubjectScoreResponse {

    private String koreanScore;

    private String socialScore;

    private String historyScore;

    private String mathScore;

    private String scienceScore;

    private String englishScore;

    private String techAndHomeScore;

}

