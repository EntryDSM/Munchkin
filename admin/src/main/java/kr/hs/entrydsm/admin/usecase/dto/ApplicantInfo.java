package kr.hs.entrydsm.admin.usecase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicantInfo {

    private String applicantTel;

    private String parentTel;

    private String homeTel;

}
