package kr.hs.entrydsm.application.entity;

import java.util.Optional;

public interface GraduationApplicationRepository {
    Iterable<GraduationApplication> findAll();
    Optional<GraduationApplication> findByReceiptCode(Long receiptCode);
}
