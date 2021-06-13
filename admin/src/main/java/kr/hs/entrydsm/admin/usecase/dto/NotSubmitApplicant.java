package kr.hs.entrydsm.admin.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotSubmitApplicant {

    private String applicantTel;

    private String parentTel;

    private String homeTel;

    private String schoolTel;
}
