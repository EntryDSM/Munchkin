package kr.hs.entrydsm.admin.integrate.applicaton;

import java.net.MalformedURLException;

import kr.hs.entrydsm.admin.usecase.dto.applicant.ApplicantInfo;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ExcelUserInfo;

public interface ApplicationRepository {
    ApplicantInfo getApplicantInfo(long receiptCode);
    ExcelUserInfo getExcelUserInfo(long receiptCode);
    String getPhotoUrl(String photoFileName) throws MalformedURLException;
}
