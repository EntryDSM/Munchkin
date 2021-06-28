package kr.hs.entrydsm.application.usecase.dto.score.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtcScoreResponse {

    private Integer volunteerTime;

    private Integer dayAbsenceCount;

    private Integer lectureAbsenceCount;

    private Integer latenessCount;

    private Integer earlyLeaveCount;

}
