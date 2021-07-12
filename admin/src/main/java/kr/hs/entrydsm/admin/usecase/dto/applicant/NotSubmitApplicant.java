package kr.hs.entrydsm.admin.usecase.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotSubmitApplicant {

    private String email;

    private String applicantTel;

    private String parentTel;

    private String homeTel;

    private String schoolTel;
}
