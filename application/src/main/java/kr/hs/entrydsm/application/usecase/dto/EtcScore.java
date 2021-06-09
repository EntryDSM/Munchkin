package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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

}
