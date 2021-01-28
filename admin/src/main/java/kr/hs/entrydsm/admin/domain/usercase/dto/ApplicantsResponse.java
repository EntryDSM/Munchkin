package kr.hs.entrydsm.admin.domain.usercase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicantsResponse {

    private Integer receiptCode;

    private String name;

    private Boolean isDaejeon;

    private String applicationType; // enum 클래스를 가져와야 할까요?

    private Boolean isArrived;

    private Boolean isPaid;

    private Boolean isFinalSubmit;

}
