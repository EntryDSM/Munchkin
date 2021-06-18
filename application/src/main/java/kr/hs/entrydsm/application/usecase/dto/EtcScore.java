package kr.hs.entrydsm.application.usecase.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EtcScore {

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

    public static EtcScore from(GraduationApplication application) {
        return EtcScore.builder()
                .volunteerTime(application.getVolunteerTime())
                .dayAbsenceCount(application.getDayAbsenceCount())
                .lectureAbsenceCount(application.getLectureAbsenceCount())
                .latenessCount(application.getLatenessCount())
                .earlyLeaveCount(application.getEarlyLeaveCount())
                .build();
    }

}
