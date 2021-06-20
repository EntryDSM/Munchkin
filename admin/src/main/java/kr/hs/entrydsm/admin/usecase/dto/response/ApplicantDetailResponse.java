package kr.hs.entrydsm.admin.usecase.dto.response;

import kr.hs.entrydsm.admin.usecase.dto.Evaluation;
import kr.hs.entrydsm.admin.usecase.dto.PersonalData;
import kr.hs.entrydsm.admin.usecase.dto.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ApplicantDetailResponse {

    private Evaluation evaluation;

    private PersonalData personalData;

    private Status status;

}
