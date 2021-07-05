package kr.hs.entrydsm.score.integrate.user;

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

    public enum ApplicationType {
        COMMON, MEISTER, SOCIAL
    }


    private final EducationalStatus educationalStatus;

    public enum EducationalStatus {
        PROSPECTIVE_GRADUATE, GRADUATE, QUALIFICATION_EXAM
    }


    public boolean isMeister() {
        return applicationType == ApplicationType.MEISTER;
    }

    public boolean isQualificationExam() {
        return educationalStatus == EducationalStatus.QUALIFICATION_EXAM;
    }

    public boolean isGraduated() {
        return educationalStatus == EducationalStatus.GRADUATE;
    }

    public boolean isProspectiveGraduate() {
        return educationalStatus == EducationalStatus.PROSPECTIVE_GRADUATE;
    }
}
