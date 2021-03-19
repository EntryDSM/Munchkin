package kr.hs.entrydsm.admin.usecase;

import java.io.IOException;

public interface ExcelService {
    void createAdmissionTicket(int receiptCode) throws IOException;
}
