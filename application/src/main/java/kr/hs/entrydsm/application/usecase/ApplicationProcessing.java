package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.domain.entity.School;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationProcessing {

    void writeSelfIntroduce(Long receiptCode, String content);

    void writeStudyPlan(Long receiptCode, String content);

    String getSelfIntroduce(Long receiptCode);

    String getStudyPlan(Long receiptCode);

    Page<School> getSchoolsByInformation(String information, Pageable pageable);

    void writeApplicationType(Long receiptCode, Application applicationRequest);

    void writeInformation(Long receiptCode, Information information);

    Application getApplicationType(Long receiptCode);

    Information getInformation(Long receiptCode);

}
