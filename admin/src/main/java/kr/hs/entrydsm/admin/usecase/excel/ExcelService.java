package kr.hs.entrydsm.admin.usecase.excel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelService {
    void createAdmissionTicket(HttpServletResponse response, long receiptCode) throws IOException;
    void createApplicantInformation(HttpServletResponse response) throws IOException;
    void getAllExcels(HttpServletResponse response) throws IOException;
}