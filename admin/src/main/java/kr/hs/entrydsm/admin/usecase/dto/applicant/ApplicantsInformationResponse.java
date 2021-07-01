package kr.hs.entrydsm.admin.usecase.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantsInformationResponse {

    private Long receiptCode;

    private String name;

    private Boolean isDaejeon;

    private String applicationType;

    private Boolean isPrintedArrived;

    private Boolean isPaid;

    private Boolean isSubmit;

}
