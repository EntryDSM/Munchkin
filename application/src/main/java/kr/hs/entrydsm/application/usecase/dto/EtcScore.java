package kr.hs.entrydsm.application.usecase.dto;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtcScore {

    @NotNull
    private Integer volunteerTime;

    @NotNull
    private Integer dayAbsenceCount;

    @NotNull
    private Integer lectureAbsenceCount;

    @NotNull
    private Integer latenessCount;

    @NotNull
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
