package kr.hs.entrydsm.score.entity.external;

import kr.hs.entrydsm.score.entity.external.enumeration.ApplicationType;
import kr.hs.entrydsm.score.entity.external.enumeration.EducationalStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scorer {
    private long receiptCode;
    private ApplicationType applicationType;
    private EducationalStatus educationalStatus;
}
