package kr.hs.entrydsm.application.integrate.user;

import kr.hs.entrydsm.application.usecase.dto.application.Information;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;

public interface ApplicationApplicantRepository {
    void writeApplicationType(Long receiptCode, ApplicationRequest application);
    void writeInformation(Long receiptCode, Information information);
    ApplicationResponse getApplicationType(Long receiptCode);
    Information getInformation(Long receiptCode);
    void setPhotoFileName(Long receiptCode, String photoFileName);
    String getPhotoFileName(Long receiptCode);
    String getEducationalStatus(Long receiptCode);
}
