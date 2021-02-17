package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.FieldNotExistException;
import kr.hs.entrydsm.score.integrate.user.Scorer;
import kr.hs.entrydsm.score.integrate.user.enumeration.ApplicationType;
import kr.hs.entrydsm.score.integrate.user.enumeration.EducationalStatus;
import org.junit.jupiter.api.Test;

public class ExternalEntityTest {
    @Test
    public void testInvalidExternalEntity() {
        try {
            Scorer.builder().receiptCode(0)
                            .applicationType(null)
                            .educationalStatus(null)
                            .build();
        } catch (FieldNotExistException e) {
            return;
        }
        throw new RuntimeException("null check test failed");
    }

    @Test
    public void testValidExternalEntity() {
        Scorer.builder().receiptCode(0)
                        .applicationType(ApplicationType.COMMON)
                        .educationalStatus(EducationalStatus.PROSPECTIVE_GRADUATE);
    }
}
