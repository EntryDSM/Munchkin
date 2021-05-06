package kr.hs.entrydsm.admin.usecase;

import java.io.IOException;

public interface ExcelService {
    void createAdmissionTicket(long receiptCode) throws IOException;
    void createApplicantInformation();
}