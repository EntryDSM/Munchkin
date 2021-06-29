package kr.hs.entrydsm.application.usecase.dto.score.request;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EtcScoreRequest {

    @PositiveOrZero
    private final Integer volunteerTime;

    @PositiveOrZero
    private final Integer dayAbsenceCount;

    @PositiveOrZero
    private final Integer lectureAbsenceCount;

    @PositiveOrZero
    private final Integer latenessCount;

    @PositiveOrZero
    private final Integer earlyLeaveCount;

    public static EtcScoreRequest from(GraduationApplication application) {
        return EtcScoreRequest.builder()
                .volunteerTime(application.getVolunteerTime())
                .dayAbsenceCount(application.getDayAbsenceCount())
                .lectureAbsenceCount(application.getLectureAbsenceCount())
                .latenessCount(application.getLatenessCount())
                .earlyLeaveCount(application.getEarlyLeaveCount())
                .build();
    }

}
