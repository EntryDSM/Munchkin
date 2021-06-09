package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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

}
