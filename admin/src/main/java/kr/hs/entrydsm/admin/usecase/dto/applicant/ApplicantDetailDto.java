package kr.hs.entrydsm.admin.usecase.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantDetailDto {

    private Evaluation evaluation;

    private PersonalData personalData;

    private Status status;

}
