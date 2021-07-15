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
                                     boolean isDaejeon,
                                     ApplicationType applicationType,
                                     EducationalStatus educationalStatus) {
        Scorer scorer = new Scorer(receiptCode,
                isDaejeon,
                applicationType,
                educationalStatus);
        return nullCheck(scorer);
    }

    private final long receiptCode;

    private final boolean isDaejeon;

    private final ApplicationType applicationType;

    public enum ApplicationType {
        COMMON, MEISTER, SOCIAL
    }

    private final EducationalStatus educationalStatus;

    public enum EducationalStatus {
        PROSPECTIVE_GRADUATE, GRADUATE, QUALIFICATION_EXAM
    }

    public boolean isDaejeon() {
        return isDaejeon;
    }

    public boolean isMeister() {
        return applicationType == ApplicationType.MEISTER;
    }

    public boolean isCommon() {
        return applicationType == ApplicationType.COMMON;
    }

    public boolean isQualificationExam() {
        return educationalStatus == EducationalStatus.QUALIFICATION_EXAM;
    }

    public boolean isProspectiveGraduate() {
        return educationalStatus == EducationalStatus.PROSPECTIVE_GRADUATE;
    }

    public boolean isGraduated() {
        return educationalStatus == EducationalStatus.GRADUATE;
    }

}
