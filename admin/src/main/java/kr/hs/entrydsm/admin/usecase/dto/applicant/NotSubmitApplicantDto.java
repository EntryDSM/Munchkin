package kr.hs.entrydsm.admin.usecase.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotSubmitApplicantDto {

    private String email;

    private String applicantTel;

    private String parentTel;

    private String homeTel;

    private String schoolTel;

}
