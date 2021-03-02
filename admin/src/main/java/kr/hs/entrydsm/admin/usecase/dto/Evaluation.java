package kr.hs.entrydsm.admin.usecase.dto;

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

    private BigDecimal volunteerTime; //봉사 시간

    private BigDecimal conversionScore; //총 점수

    private Integer dayAbsenceCount; //무단 결석

    private Integer lectureAbsenceCount; //무단 결과

    private Integer earlyLeaveCount; //조퇴
    
    private Integer latenessCount; //지각
    
    private String selfIntroduce; //자기소개서
    
    private String studyPlan; //학업계획서

    private BigDecimal averageScore; //검정고시 총 점수

}
