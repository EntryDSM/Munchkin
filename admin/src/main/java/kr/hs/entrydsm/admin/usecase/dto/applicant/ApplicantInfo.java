package kr.hs.entrydsm.admin.usecase.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class ApplicantInfo {

    private BigDecimal averageScore;

    private String schoolName;

    private String schoolTel;

    private Integer volunteerTime;

    private BigDecimal conversionScore;

    private Integer dayAbsenceCount;

    private Integer lectureAbsenceCount;

    private Integer earlyLeaveCount;

    private Integer latenessCount;

    private Boolean isGraduated;

}
