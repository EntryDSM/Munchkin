package kr.hs.entrydsm.admin.usecase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptStatusResponse {

    private Integer totalApplicantCount;

    private Double totalCompetitionRate;

    private CommonScoreResponse commonScore;

    private SpecialScoreResponse meisterScore;

    private SpecialScoreResponse socialScore;

    private Integer commonCount;

    private Double commonCompetitionRate;

    private Integer meisterCount;

    private Double meisterCompetitionRate;

    private Integer socialCount;

    private Double socialCompetitionRate;

}
