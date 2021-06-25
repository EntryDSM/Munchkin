package kr.hs.entrydsm.application.integrate.user;

import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.request.InformationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.InformationResponse;

public interface ApplicationApplicantRepository {
    void writeApplicationType(Long receiptCode, ApplicationRequest application);
    void writeInformation(Long receiptCode, InformationRequest information);
    ApplicationResponse getApplicationType(Long receiptCode);
    InformationResponse getInformation(Long receiptCode);
    void setPhotoFileName(Long receiptCode, String photoFileName);
    String getPhotoFileName(Long receiptCode);
}
