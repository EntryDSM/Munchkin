package kr.hs.entrydsm.user.integrate.score;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;

import java.util.List;

@Published
public interface UserIntegrateScoreRepository {
    User findByReceiptCode(long receiptCode);
    User findByReceiptCodeAndIsSubmitTrue(long receiptCode);
    List<User> findCandidatesByRegionAndType(boolean isDaejeon, ApplicationType applicationType);
}
