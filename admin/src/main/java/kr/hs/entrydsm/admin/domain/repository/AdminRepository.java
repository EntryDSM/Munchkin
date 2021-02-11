package kr.hs.entrydsm.admin.domain.repository;

import kr.hs.entrydsm.admin.domain.entity.Admin;

import java.util.Optional;

public interface AdminRepository {
    Optional<Admin> findById(String id);
    void changeExamCode(int receiptCode, String examCode);
}
