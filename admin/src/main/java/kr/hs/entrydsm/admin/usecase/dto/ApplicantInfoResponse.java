package kr.hs.entrydsm.admin.usecase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicantInfoResponse {

    private ApplicantInfo applicantInfo;

    private String schoolTel;

}
