package kr.hs.entrydsm.admin.usecase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicantsInformationResponse {

    private Integer receiptCode;

    private String name;

    private Boolean isDaejeon;

    private String applicationType;

    private Boolean isPrintedArrived;

    private Boolean isPaid;

    private Boolean isSubmit;

}
