package kr.hs.entrydsm.admin.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NotSubmitApplicant {

    private String applicantTel;

    private String parentTel;

    private String homeTel;

    private String schoolTel;
}
