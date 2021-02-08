package kr.hs.entrydsm.admin.usecase.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ApplicantsResponse extends PageResponse {

    private List<ApplicantsInformationResponse> applicantsInformationResponses;

    @Builder
    public ApplicantsResponse(Integer totalElements, Integer totalPages, List<ApplicantsInformationResponse> applicantsInformationResponses) {
        super(totalElements, totalPages);
        this.applicantsInformationResponses = applicantsInformationResponses;
    }
}
