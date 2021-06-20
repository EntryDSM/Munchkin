package kr.hs.entrydsm.admin.usecase.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ApplicantsInformationResponse {

    private Long receiptCode;

    private String name;

    private Boolean isDaejeon;

    private String applicationType;

    private Boolean isPrintedArrived;

    private Boolean isPaid;

    private Boolean isSubmit;

}
