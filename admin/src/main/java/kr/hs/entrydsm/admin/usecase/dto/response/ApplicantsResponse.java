package kr.hs.entrydsm.admin.usecase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantsResponse extends PageResponse {

    private List<ApplicantsInformationResponse> applicantsInformationResponses;

    @Builder
    public ApplicantsResponse(Integer totalElements, Integer totalPages, List<ApplicantsInformationResponse> applicantsInformationResponses) {
        super(totalElements, totalPages);
        this.applicantsInformationResponses = applicantsInformationResponses;
    }
}
