package kr.hs.entrydsm.application.integrate.score;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.common.context.beans.Published;

@Published
public interface ApplicationExportRepository {
    GraduationApplication getGraduationApplication(long receiptCode);
    QualificationExamApplication getQualificationExamApplication(long receiptCode);
    void saveGraduationApplication(GraduationApplication application);
    void saveQualificationExamApplication(QualificationExamApplication application);
}
