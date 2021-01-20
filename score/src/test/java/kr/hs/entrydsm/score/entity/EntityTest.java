package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import kr.hs.entrydsm.score.integrate.user.enumeration.ApplicationType;
import kr.hs.entrydsm.score.integrate.user.enumeration.EducationalStatus;

public abstract class EntityTest {
    protected final Scorer prospectiveGraduateCommon = Scorer.builder()
                                                             .educationalStatus(EducationalStatus.PROSPECTIVE_GRADUATE)
                                                             .applicationType(ApplicationType.COMMON)
                                                             .build();

    protected final Scorer graduateCommon = Scorer.builder()
                                                  .educationalStatus(EducationalStatus.GRADUATE)
                                                  .applicationType(ApplicationType.COMMON)
                                                  .build();

    protected final Scorer prospectiveGraduateMeister = Scorer.builder()
                                                              .educationalStatus(EducationalStatus.PROSPECTIVE_GRADUATE)
                                                              .applicationType(ApplicationType.MEISTER)
                                                              .build();

    protected final Scorer graduateMeister = Scorer.builder()
                                                   .educationalStatus(EducationalStatus.GRADUATE)
                                                   .applicationType(ApplicationType.MEISTER)
                                                   .build();

    protected final Scorer[] scorers = new Scorer[]{prospectiveGraduateCommon,
                                                    prospectiveGraduateMeister,
                                                    graduateCommon,
                                                    graduateMeister};
}
