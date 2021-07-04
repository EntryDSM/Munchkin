package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.user.Scorer;

public abstract class EntityTest {
    protected final Scorer prospectiveGraduateCommon = Scorer.builder()
                                                             .educationalStatus(Scorer.EducationalStatus.PROSPECTIVE_GRADUATE)
                                                             .applicationType(Scorer.ApplicationType.COMMON)
                                                             .build();

    protected final Scorer graduateCommon = Scorer.builder()
                                                  .educationalStatus(Scorer.EducationalStatus.GRADUATE)
                                                  .applicationType(Scorer.ApplicationType.COMMON)
                                                  .build();

    protected final Scorer prospectiveGraduateMeister = Scorer.builder()
                                                              .educationalStatus(Scorer.EducationalStatus.PROSPECTIVE_GRADUATE)
                                                              .applicationType(Scorer.ApplicationType.MEISTER)
                                                              .build();

    protected final Scorer graduateMeister = Scorer.builder()
                                                   .educationalStatus(Scorer.EducationalStatus.GRADUATE)
                                                   .applicationType(Scorer.ApplicationType.MEISTER)
                                                   .build();

    protected final Scorer[] scorers = new Scorer[]{prospectiveGraduateCommon,
                                                    prospectiveGraduateMeister,
                                                    graduateCommon,
                                                    graduateMeister};
}
