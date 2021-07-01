package kr.hs.entrydsm.admin.usecase.dto.response;

import kr.hs.entrydsm.admin.usecase.dto.CommonScore;
import kr.hs.entrydsm.admin.usecase.dto.SpecialScore;
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

    private CommonScore commonScore;

    private SpecialScore meisterScore;

    private SpecialScore socialScore;

    private Integer commonCount;

    private Double commonCompetitionRate;

    private Integer meisterCount;

    private Double meisterCompetitionRate;

    private Integer socialCount;

    private Double socialCompetitionRate;

}
