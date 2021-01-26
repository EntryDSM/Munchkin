package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.domain.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationProcessing {

    void writeSelfIntroduce(Long receiptCode, String content);

    void writeStudyPlan(Long receiptCode, String content);

    String getSelfIntroduce(Long receiptCode);

    String getStudyPlan(Long receiptCode);

    Page<School> getSchoolsByInformation(String information, Pageable pageable);
}
