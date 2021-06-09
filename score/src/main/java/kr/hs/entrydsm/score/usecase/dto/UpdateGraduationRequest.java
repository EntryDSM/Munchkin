package kr.hs.entrydsm.score.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Builder
@Getter
@AllArgsConstructor
public class UpdateGraduationRequest {
    @PositiveOrZero
    private final int volunteerTime;

    @PositiveOrZero
    private final int dayAbsenceCount;

    @PositiveOrZero
    private final int lectureAbsenceCount;

    @PositiveOrZero
    private final int latenessCount;

    @PositiveOrZero
    private final int earlyLeaveCount;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String koreanGrade;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String socialGrade;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String historyGrade;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String mathGrade;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String scienceGrade;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String englishGrade;

    @Pattern(regexp = "[A-E,X]{6}")
    private final String techAndHomeGrade;
}
