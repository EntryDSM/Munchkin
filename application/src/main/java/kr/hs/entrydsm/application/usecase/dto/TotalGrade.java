package kr.hs.entrydsm.application.usecase.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@Builder
@Getter
public class TotalGrade {

    @NotNull
    private final SubjectScore subjectScore;

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


}
