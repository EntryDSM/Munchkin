package kr.hs.entrydsm.admin.usecase.dto.response;

import kr.hs.entrydsm.admin.usecase.dto.CommonScore;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReceiptStatusResponse {

    private Integer totalApplicantCount; //총 지원자

    private Integer totalCompetitionRate; //총 경쟁률

    private CommonScore commonScore; //일반전형 점수

    private SpecialScore meisterScore; //마이스터 전형 점수

    private SpecialScore socialScore; //사회통합 전형 점수

    private Integer commonCount;

    private double commonCompetitionRate;

    private double meisterCount;

    private double meisterCompetitionRate;

    private double socialCount;

    private double socialCompetitionRate;

}
