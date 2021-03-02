package kr.hs.entrydsm.application.domain.repository;

import kr.hs.entrydsm.application.domain.entity.Applicant;
import kr.hs.entrydsm.application.domain.entity.Application;

import java.util.Optional;

public interface ApplicationRepository {

    Optional<Application> findByReceiptCode(Long receiptCode);
}
