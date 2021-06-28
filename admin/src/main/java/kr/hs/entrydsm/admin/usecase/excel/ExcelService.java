package kr.hs.entrydsm.admin.usecase.excel;

import java.io.IOException;

public interface ExcelService {
    void createAdmissionTicket(long receiptCode) throws IOException;
    void createApplicantInformation() throws IOException;
    void getAllExcels() throws IOException;
}