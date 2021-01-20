package kr.hs.entrydsm.score.integrate.user;

import kr.hs.entrydsm.score.integrate.user.enumeration.ApplicationType;
import kr.hs.entrydsm.score.integrate.user.enumeration.EducationalStatus;
import kr.hs.entrydsm.score.integrate.ExternalEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Scorer extends ExternalEntity {
    @Builder
    public static Scorer initializer(long receiptCode,
                                     ApplicationType applicationType,
                                     EducationalStatus educationalStatus) {
        Scorer scorer = new Scorer(receiptCode,
                applicationType,
                educationalStatus);
        return nullCheck(scorer);
    }

    private final long receiptCode;
    private final ApplicationType applicationType;
    private final EducationalStatus educationalStatus;

    public boolean isMeister() { return applicationType == ApplicationType.MEISTER; }
    public boolean isQualificationExam() { return educationalStatus == EducationalStatus.QUALIFICATION_EXAM; }
    public boolean isGraduated() { return educationalStatus == EducationalStatus.GRADUATE; }
}
