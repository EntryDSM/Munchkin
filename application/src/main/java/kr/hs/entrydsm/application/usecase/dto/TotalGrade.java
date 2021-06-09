package kr.hs.entrydsm.application.usecase.dto;

import kr.hs.entrydsm.application.entity.GraduationApplication;
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

    public static TotalGrade fromApplicationAndSubjectScore(GraduationApplication application, SubjectScore subjectScore) {
        return TotalGrade.builder()
                .subjectScore(subjectScore)
                .dayAbsenceCount(application.getDayAbsenceCount())
                .earlyLeaveCount(application.getEarlyLeaveCount())
                .latenessCount(application.getLatenessCount())
                .lectureAbsenceCount(application.getLectureAbsenceCount())
                .volunteerTime(application.getVolunteerTime())
                .build();
    }
}
