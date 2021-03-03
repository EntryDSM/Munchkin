package kr.hs.entrydsm.application.integrate.user;

import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantExportManager implements ApplicantExportRepository {


    @Override
    public void writeApplicationType(Long receiptCode, Application application) {

    }

    @Override
    public void writeInformation(Long receiptCode, Information information) {

    }

    @Override
    public Application getApplicationType(Long receiptCode) {
        return null;
    }

    @Override
    public Information getInformation(Long receiptCode) {
        return null;
    }
}
