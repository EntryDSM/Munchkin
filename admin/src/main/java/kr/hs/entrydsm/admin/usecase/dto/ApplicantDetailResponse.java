package kr.hs.entrydsm.admin.usecase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicantDetailResponse {

    private Evaluation evaluation;

    private PersonalData personalData;

    private Status status;

    private ApplicantInfo applicantInfo;

}
