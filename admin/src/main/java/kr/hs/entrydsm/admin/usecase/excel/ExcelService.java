package kr.hs.entrydsm.admin.usecase.excel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelService {
    void createAdmissionTicket(HttpServletResponse response) throws Exception;
    void createApplicantInformation(HttpServletResponse response) throws IOException;
}