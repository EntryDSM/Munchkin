package kr.hs.entrydsm.admin.usecase.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {

    private Integer volunteerTime;

    private BigDecimal conversionScore;

    private Integer dayAbsenceCount;

    private Integer lectureAbsenceCount;

    private Integer earlyLeaveCount;
    
    private Integer latenessCount;
    
    private String selfIntroduce;
    
    private String studyPlan;

    private BigDecimal averageScore;

}
