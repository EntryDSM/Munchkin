package kr.hs.entrydsm.application.usecase.dto.score.request;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EtcScoreRequest {

    @PositiveOrZero
    private Integer volunteerTime;

    @PositiveOrZero
    private Integer dayAbsenceCount;

    @PositiveOrZero
    private Integer lectureAbsenceCount;

    @PositiveOrZero
    private Integer latenessCount;

    @PositiveOrZero
    private Integer earlyLeaveCount;

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
